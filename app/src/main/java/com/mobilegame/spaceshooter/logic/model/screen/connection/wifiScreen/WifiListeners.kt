package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen

import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.util.Log
import java.io.IOException
import java.net.UnknownHostException

class WifiListeners(val vm: WifiConnectionViewModel) {
    fun getResolveListener(): NsdManager.ResolveListener = object : NsdManager.ResolveListener {

        val TAG = "resolveListener"

        override fun onResolveFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
            // Called when the resolve fails. Use the error code to debug.
            Log.e(TAG, "Resolve failed: $errorCode")
        }

        override fun onServiceResolved(serviceInfo: NsdServiceInfo) {
            Log.i(TAG, "Resolve Succeeded. $serviceInfo")

            vm.info.socket?.let {
                Log.i(TAG, "Socket already connected $it")
                return
            }

            try {
                vm.connectToServer(serviceInfo)
            } catch (e: UnknownHostException) {
                Log.e(TAG, "Unknown host. ${e.localizedMessage}")
            } catch (e: IOException) {
                Log.e(TAG, "Failed to create writer. ${e.localizedMessage}")
            }
        }
    }

    var discoveryListener = getADiscoveryListener()

    fun getADiscoveryListener(): NsdManager.DiscoveryListener = object : NsdManager.DiscoveryListener {

        val TAG = "discoveryListener"

        // Called as soon as service discovery begins.
        override fun onDiscoveryStarted(regType: String) {
            Log.d(TAG, "Service discovery started")
        }

        override fun onServiceFound(service: NsdServiceInfo) {
            Log.d(TAG, "Service found ${service.serviceName}")

            // A service was found! Do something with it.
            if (service.serviceName == vm.info.networkSearchDiscoveryName) {
                vm.info.nsdManager.resolveService(service, getResolveListener())
            }
        }

        override fun onServiceLost(service: NsdServiceInfo) {
            // When the network service is no longer available.
            // Internal bookkeeping code goes here.
            Log.e(TAG, "service lost: $service")
        }

        override fun onDiscoveryStopped(serviceType: String) {
            Log.i(TAG, "Discovery stopped: $serviceType")
        }

        override fun onStartDiscoveryFailed(serviceType: String, errorCode: Int) {
            Log.e(TAG, "Discovery failed: Error code:$errorCode")
            vm.info.nsdManager.stopServiceDiscovery(this)
        }

        override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {
            Log.e(TAG, "Discovery failed: Error code:$errorCode")
            vm.info.nsdManager.stopServiceDiscovery(this)
        }
    }

    val registrationListener = object : NsdManager.RegistrationListener {
        override fun onServiceRegistered(NsdServiceInfo: NsdServiceInfo) {
            // Save the service name. Android may have changed it in order to
            // resolve a conflict, so update the name you initially requested
            // with the name Android actually used.
            Log.d("NsdManager.Registration", "Service registered as \"${NsdServiceInfo.serviceName}\"")
            if (NsdServiceInfo.serviceName != vm.info.networkSearchDiscoveryName) {
                Log.w("NsdManager.Registration", "registration issue")
                discoveryListener = getADiscoveryListener()
                vm.restart()
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
