package com.mobilegame.spaceshooter.logic.repository.connection

import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.util.Log
import com.mobilegame.spaceshooter.data.connection.wifi.PreparationState
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiClient
import com.mobilegame.spaceshooter.data.connection.wifi.WifiLinkState
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiInfoService
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.channel.WifiChannel
import com.mobilegame.spaceshooter.logic.repository.device.DeviceWifiRepo
import com.mobilegame.spaceshooter.utils.extensions.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.io.IOException
import java.io.PrintWriter
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketException

class WifiChannelsWithClientRepo() {
    val TAG = "WifiChannelsToClientRepo"
    private val repo = DeviceWifiRepo()

    fun getServerSocket(): ServerSocket? = Device.wifi.channels.serverSocket
    fun setServerSocket(serverSocket: ServerSocket?) { Device.wifi.channels.serverSocket = serverSocket }
//    fun getServerInetAddress(): InetAddress? = Device.wifi.channels.serverInetAddress
//    fun setServerInetAddress(inetAddress: InetAddress?) { Device.wifi.channels.serverInetAddress = inetAddress }
    fun isHosting(): Boolean = getServerSocket()?.let { true } ?: false

    fun getClientsList(): List<WifiInfoService> = Device.wifi.channels.withClients.map { it.info!! }
    fun withClientChannels(): MutableList<WifiChannel> = Device.wifi.channels.withClients
    suspend fun startHostingNewClients() = runBlocking(Dispatchers.IO) {
        val fTAG = "startHostingNewClients"
        Log.e(TAG, "$fTAG: start")
        // Create a listen socket
        initializeServerSocket()
        getServerSocket()?.let { _serverSocket ->
            registerService(_serverSocket.localPort)
//            DeviceWifiRepo().updateLinkStateTo(WifiLinkState.ConnectedAsServer)

            val hostName = Device.data.name
            Log.e(TAG, "$fTAG: $hostName ${Device.wifi.inetAddress}")
//            setServerInetAddress(Device.wifi.inetAddress)
//            Log.e(TAG, "$fTAG: setServerInetAddress ${Device.wifi.inetAddress}")

            //Listen for new Client
            Log.i(TAG, "$fTAG: loop on isHosting")
            while ( isHosting() ) {
                try {
                    _serverSocket.accept()?.let {
                        if (it.inetAddress != repo.getLocalIp()) {
                            Log.w(TAG, "$fTAG accept new client ${it.inetAddress}")
                            registerNewClient(it)?.let {
                                Log.e(TAG, "$fTAG registerNewClient: accepted client ${it.name} ${it.socket.inetAddress}")
                                // Start reading messages
                                async {
                                    withClientChannels().add(it)
                                    withClientChannels().open(it)
                                }
                            } ?: run {
                                Log.w(TAG, "$fTAG: register fail")
                                return@run }
                        } else Log.i(TAG, "$fTAG: found mySelf")
                    }
                } catch (e: SocketException) {
                    Log.w(TAG, "$fTAG: socketExcetpion")
                    break
                }
            }
            Log.e(TAG, "$fTAG: Stop")
        }
    }

    fun stopHosting() {
        Log.e(TAG, "stopHosting")
        // Remove the clients
//        withClientChannels().forEach { Device.wifi.channels.withClients.close(it) }
        withClientChannels().closeAll()
        withClientChannels().removeAll(Device.wifi.channels.withClients)
        repo.getNsdManager().unregisterService(registrationListener)
        repo.resetVisibleDevice()
        // Stop Hosting
        getServerSocket()?.close()
        setServerSocket(null)
//        setServerInetAddress(null)
        repo.updateLinkStateTo(WifiLinkState.NotConnected)
    }

    private fun initializeServerSocket() {
        val fTAG = "initializeServerSocket"
        Log.i(TAG, "$fTAG: ")
        var localPort: Int
        // Initialize a server socket on the next available port.
        setServerSocket(ServerSocket(0).also { socket ->
            // Store the chosen port.
            localPort = socket.localPort
        })
        Log.i(TAG, "$fTAG : server socket localPort : $localPort")
    }

    private fun registerNewClient(socket: Socket): WifiClient? {
        Log.i(TAG, "registerNewClient: inetAddress ${socket.inetAddress}")
        Log.i(TAG, "registerNewClient: port ${socket.port}")
        Log.i(TAG, "registerNewClient: localport ${socket.localPort}")
        Log.i(TAG, "registerNewClient: localAddress ${socket.localAddress}")
        Log.e(TAG, "registerNewClient: setinetAddress to ${socket.localAddress}")
        Device.wifi.inetAddress = socket.localAddress
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
