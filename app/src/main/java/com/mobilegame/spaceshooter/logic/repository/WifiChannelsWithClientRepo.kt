package com.mobilegame.spaceshooter.logic.repository

import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.util.Log
import com.mobilegame.spaceshooter.data.connection.wifi.PreparationState
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiClient
import com.mobilegame.spaceshooter.data.connection.wifi.WifiLinkState
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiInfoService
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.channel.WifiChannel
import com.mobilegame.spaceshooter.utils.extensions.add
import com.mobilegame.spaceshooter.utils.extensions.close
import com.mobilegame.spaceshooter.utils.extensions.getInfo
import com.mobilegame.spaceshooter.utils.extensions.open
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.io.IOException
import java.io.PrintWriter
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketException
import kotlin.math.log

class WifiChannelsWithClientRepo() {
    val TAG = "WifiChannelsToClientRepo"
    private val repo = DeviceWifiRepo()

    fun getServerSocket(): ServerSocket? = Device.wifi.channels.serverSocket
    fun setServerSocket(serverSocket: ServerSocket?) { Device.wifi.channels.serverSocket = serverSocket }
    fun isHosting(): Boolean = getServerSocket()?.let { true } ?: false

    fun getClientsList(): List<WifiInfoService> = Device.wifi.channels.withClients.map { it.info!! }
    fun withClientChannels(): MutableList<WifiChannel> = Device.wifi.channels.withClients
    suspend fun startHostingNewClients() = runBlocking(Dispatchers.IO) {
        Log.e(TAG, "startHostingNewClients: start")
        // Create a listen socket
        initializeServerSocket()
        getServerSocket()?.let { _serverSocket ->
            registerService(_serverSocket.localPort)
//            DeviceWifiRepo().updateLinkStateTo(WifiLinkState.ConnectedAsServer)

            val hostName = Device.data.name
            Log.e(TAG, "startHostingNewClients: $hostName ${Device.wifi.inetAddress}")

            //Listen for new Client
            Log.i(TAG, "startHostingNewClients: loop on isHosting")
            while ( isHosting() ) {
                try {
                    _serverSocket.accept()?.let {
                        if (it.inetAddress != repo.getLocalIp()) {
                            Log.w(TAG, "startHostingNewClients: accept new client ${it.inetAddress}")
                            registerNewClient(it)?.let {
                                Log.e(TAG, "registerNewClient: accepted client ${it.name} ${it.socket.inetAddress}")
                                // Start reading messages
                                async {
                                    withClientChannels().add(it)
                                    withClientChannels().open(it)
                                }
                            } ?: run {
                                Log.w(TAG, "startHostingNewClients: register fail")
                                return@run }
                        } else Log.i(TAG, "startHostingNewClients: found mySelf")
                    }
                } catch (e: SocketException) {
                    Log.w(TAG, "startHostingNewClients: socketExcetpion")
                    break
                }
            }
            Log.e(TAG, "startHostingNewClients: Stop")
        }
    }

    fun stopHosting() {
        Log.e(TAG, "stopHosting")
        // Remove the clients
        withClientChannels().forEach { Device.wifi.channels.withClients.close(it) }
        withClientChannels().removeAll(Device.wifi.channels.withClients)
        repo.getNsdManager().unregisterService(registrationListener)
        repo.resetVisibleDevice()
        // Stop Hosting
        getServerSocket()?.close()
        setServerSocket(null)
        repo.updateLinkStateTo(WifiLinkState.NotConnected)
    }

    private fun initializeServerSocket() {
        Log.i(TAG, "initializeServerSocket: ")
        var localPort: Int
        // Initialize a server socket on the next available port.
        setServerSocket(ServerSocket(0).also { socket ->
            // Store the chosen port.
            localPort = socket.localPort
        })
        Log.i(TAG, "initializeServerSocket: server socket localPort : $localPort")
    }

    private fun registerNewClient(socket: Socket): WifiClient? {
        Log.i(TAG, "registerNewClient: inetAddress ${socket.inetAddress}")
        Log.i(TAG, "registerNewClient: port ${socket.port}")
        Log.i(TAG, "registerNewClient: localport ${socket.localPort}")
        Log.i(TAG, "registerNewClient: localAddress ${socket.localAddress}")
        Log.i(TAG, "registerNewClient: outputStream ${socket.getOutputStream()}")
        val writer: PrintWriter
        try {
            writer = PrintWriter(socket.getOutputStream())
        } catch (e: IOException) {
            Log.w(TAG, "Failed to create writer for $socket")
            return null
        }
        Log.i(TAG, "registerNewClient: bef")
        val newClient = WifiClient("", PreparationState.Waiting, socket, writer)
        Log.i(TAG, "registerNewClient: end")
        return newClient
    }

    private fun registerService(port: Int) {
        Log.d(TAG, "registerservice: nsd ${repo.getNsdManager()}")
        val serviceInfo = NsdServiceInfo().apply {
            serviceName = Device.wifi.networkSearchDiscoveryName
            serviceType = Device.wifi.type
            setPort(port)
        }
        repo.getNsdManager().registerService(serviceInfo, NsdManager.PROTOCOL_DNS_SD, registrationListener)
        Log.d(TAG, "register service: ${serviceInfo.serviceName} ${serviceInfo.port} ${serviceInfo.host} ${serviceInfo.serviceType}")
    }

    fun getConnectedClient(inetAddress: InetAddress): WifiClient? = withClientChannels().getInfo(inetAddress) as WifiClient?
    fun updateConnectedClientName(inetAddress: InetAddress, newName: String) {
        getConnectedClient(inetAddress)?.name = newName
    }

    val registrationListener = object : NsdManager.RegistrationListener {
        override fun onServiceRegistered(NsdServiceInfo: NsdServiceInfo) {
            // Save the service name. Android may have changed it in order to
            // resolve a conflict, so update the name you initially requested
            // with the name Android actually used.
            Log.d("NsdManager.Registration", "Service registered as \"${NsdServiceInfo.serviceName}\"")
            if (NsdServiceInfo.serviceName != Device.wifi.networkSearchDiscoveryName) {
                stopHosting()
                repo.updateLinkStateTo(WifiLinkState.Connecting)
//                WifiChannelsWithServerRepo().searchForServer()
            }
        }

        override fun onRegistrationFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
            // Registration failed! Put debugging code here to determine why.
            Log.d("NsdManager.Registration", "Registration failed")
        }

        override fun onServiceUnregistered(arg0: NsdServiceInfo) {
            // Service has been unregistered. This only happens when you call
            // NsdManager.unregisterService() and pass in this listener.
            Log.d("NsdManager.Registration", "Service unregistered")
        }

        override fun onUnregistrationFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
            // Unregistration failed. Put debugging code here to determine why.
            Log.d("NsdManager.Registration", "Unregistration failed")
        }
    }
}
