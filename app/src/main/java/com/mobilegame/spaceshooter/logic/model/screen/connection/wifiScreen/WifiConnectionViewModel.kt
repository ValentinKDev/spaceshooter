package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen

import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.connection.dto.EventMessage
import com.mobilegame.spaceshooter.data.connection.dto.EventMessageType
import com.mobilegame.spaceshooter.data.connection.wifi.WifiClient
import com.mobilegame.spaceshooter.data.connection.wifi.WifiLinkState
import com.mobilegame.spaceshooter.data.connection.wifi.channel.WifiChannelService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.PrintWriter
import java.lang.IllegalArgumentException
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketException

class WifiConnectionViewModel(val info: WifiConnectionInfo): ViewModel() {
    private val TAG = "WifiConnectionVM"
    private lateinit var serviceInfo: NsdServiceInfo
    private var serverSocket: ServerSocket? = null
    private val listeners = WifiListeners(this)
    fun isHosting(): Boolean = serverSocket?.let { true } ?: false


    fun start() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i(TAG, "start")
            info.updateLinkStateTo(WifiLinkState.Connecting)
            // Browse for existing services
            startSearching()
            delayUntilConnected()
            stopSearching()
//            startSearching()
//            delayUntilConnected()
//            stopSearching()
            when (info.linkState.value) {
                WifiLinkState.Connected -> startListenServer()
                WifiLinkState.Connecting -> startHosting()
                WifiLinkState.NotConnected -> Log.e(TAG, "ERROR")
            }
        }
    }

    private suspend fun delayUntilConnected() {
        Log.i(TAG, "delayUntilConnected")
        var count = 0
        while (info.linkState.value == WifiLinkState.Connecting && count < 5) {
            Log.i(TAG, "delayUntilConnected $count")
            delay(250L)
            count += 1
        }
    }

    private fun startSearching() {
        Log.i(TAG, "startSearching")
        info.nsdManager.discoverServices(
            info.type,
            NsdManager.PROTOCOL_DNS_SD,
            listeners.discoveryListener
        )
    }

    private suspend fun startListenServer() {
        Log.e(TAG, "start listen server ")
        info.socket?.let {
            WifiChannelService.createChannelToServer(info).open()
        } ?: Log.e(TAG, "startListenServer : ERROR info.socket null cannot create a channel to server")
    }

    private suspend fun startHosting() {

        // Create a listen socket
        initializeServerSocket()
        serverSocket?.let { _serverSocket ->
            registerService(_serverSocket.localPort)
            info.updateLinkStateTo(WifiLinkState.Connected)

            val hostName = info.deviceName
            Log.e(TAG, "startHosting: $hostName")

            //Listen for new Client
            viewModelScope.launch(Dispatchers.IO) {
                Log.i(TAG, "startHosting: loop on isHosting")
                while ( isHosting() ) {
                    try {
                        _serverSocket.accept()?.let {
                            Log.w(TAG, "startHosting: accept new client ${it.inetAddress}")
                            registerNewClient(it) ?: let { return@let }
                            val newClient = info.connectedClients.last()

                            // Give the client server name
//                            val serverNameEventMessage = EventMessage(EventMessageType.SendDeviceName.name, info.deviceName, "")
//                            SendEvent(info, serverNameEventMessage).toClient(newClient)

                            // Start reading messages
                            val aside = async {
                                WifiChannelService.createChannelToClient(
                                    newClient,
                                    info
                                ).open()
                            }
                        }
                    } catch (e: SocketException) {
                        break
                    }
                }
            }
        }
    }

    private fun registerNewClient(socket: Socket): Unit? {
        val name = ""
        val writer: PrintWriter

        try { writer = PrintWriter(socket.getOutputStream()) }
        catch (e: IOException) {
            Log.w(TAG, "Failed to create writer for $socket")
            return null
        }

        val newClient = WifiClient(socket, name, writer)
        Log.e(TAG, "registerNewClient: accepted client ${newClient.name}${newClient.socket.inetAddress}")
        info.connectedClients.add(newClient)
        return Unit
    }

    private fun initializeServerSocket() {
        Log.i(TAG, "initializeServerSocket:")
        var localPort: Int
        // Initialize a server socket on the next available port.
        serverSocket = ServerSocket(0).also { socket ->
            // Store the chosen port.
            localPort = socket.localPort
        }
        Log.i(TAG, "initializeServerSocket: server socket localPort : $localPort")
    }

    private fun registerService(port: Int) {
        // Create the NsdServiceInfo object, and populate it.
        serviceInfo = NsdServiceInfo().apply {
            // The name is subject to change based on conflicts
            // with other services advertised on the same network.
            serviceName = info.networkSearchDiscoveryName
            serviceType = info.type
            setPort(port)
        }
        // Register the service for discovery
        info.nsdManager.registerService(serviceInfo, NsdManager.PROTOCOL_DNS_SD, listeners.registrationListener)
        Log.d(TAG, "register service: ${serviceInfo.serviceName} ${serviceInfo.port} ${serviceInfo.host} ${serviceInfo.serviceType}")
    }

    fun stopSearching() {
        Log.i(TAG, "stopSearching")
        try {
            info.nsdManager.stopServiceDiscovery(listeners.discoveryListener)
        } catch (e: IllegalArgumentException) {
            Log.i("nsdManager", "stopSearching: discoveryListener not registered")
        }
    }

    fun stopHosting() {
        // Stop broadcasting service
        info.nsdManager.unregisterService(listeners.registrationListener)

        // Remove the clients
        info.connectedClients.forEach {
            it.writer.close()
            it.socket.close()
        }
        info.connectedClients = mutableListOf()
        info.resetVisibleDevice()

        // Stop Hosting
        serverSocket?.close()
        serverSocket = null
    }

    fun restart() {
        viewModelScope.launch {
            stopHosting()
            start()
        }
    }

    fun connectToServer(serviceInfo: NsdServiceInfo) {
        Log.i(TAG, "connectToServer")
        info.updateLinkStateTo(WifiLinkState.Connected)
        info.socket = Socket(serviceInfo.host, serviceInfo.port)
        info.writer = PrintWriter(info.socket!!.getOutputStream())
        val nameEventMessage = EventMessage(type = EventMessageType.SendDeviceName.name, sender = info.deviceName, message = info.deviceName)
//        info.socket?.getOutputStream()?.let {
//            PrintWriter(it)
//        }?.let {
//            it.print(nameEventMessage.toJson() + EventMessage.MESSAGE_TERMINATOR)
//            it.flush()
//        }
//        val nameEventMessage = EventMessage(typeKey = EventMessageType.SendDeviceName.key, sender = info.deviceName, message = info.deviceName)
        SendEvent(info, nameEventMessage).toServer()
    }
}