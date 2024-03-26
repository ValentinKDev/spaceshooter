package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen

import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.util.Log
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.repository.device.DeviceEventRepo
import com.mobilegame.spaceshooter.logic.repository.device.DeviceWifiRepo
import com.mobilegame.spaceshooter.logic.repository.connection.WifiChannelsWithClientRepo
import com.mobilegame.spaceshooter.logic.repository.connection.WifiChannelsWithServerRepo

class WifiListeners() {
    val wifiRepo = DeviceWifiRepo()
    val toServerRepo = WifiChannelsWithServerRepo()
    val toClientRepo = WifiChannelsWithClientRepo()
    val eventRepo = DeviceEventRepo()

    fun getResolveListener(): NsdManager.ResolveListener = object : NsdManager.ResolveListener {
        val TAG = "resolveListener"

        override fun onResolveFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
            // Called when the resolve fails. Use the error code to debug.
            Log.e(TAG, "Resolve failed: $errorCode")
        }

        override fun onServiceResolved(serviceInfo: NsdServiceInfo) {
            Log.i(TAG, "onServiceResolved: Resolve Succeeded. $serviceInfo")
//            Log.e(TAG, "onServiceResolved: \n\n\n\n\n ${serviceInfo.host}", )
            if (serviceInfo.host == wifiRepo.getLocalIp()) {
                Log.e(TAG, "onServiceResolved: find my self -> stop discovery")
                stopDiscovery()
            }
            toServerRepo.addServer(serviceInfo)
        }
    }

//    var discoveryListener = getADiscoveryListener()
//    fun getADiscoveryListener(): NsdManager.DiscoveryListener = object : NsdManager.DiscoveryListener {
    val discoveryListener: NsdManager.DiscoveryListener = object : NsdManager.DiscoveryListener {

        val TAG = "discoveryListener"

        // Called as soon as service discovery begins.
        override fun onDiscoveryStarted(regType: String) {
            Log.d(TAG, "Service discovery started")
        }

        override fun onServiceFound(service: NsdServiceInfo) {
            Log.d(TAG, "Service found ${service.serviceName}")

            // A service was found! Do something with it.
//            if (service.serviceName.contains(Device.wifi.networkSearchDiscoveryName)
//                || toServerRepo.contains(service.host)
            if (service.serviceName.equals(Device.wifi.networkSearchDiscoveryName) ) {
                wifiRepo.getNsdManager().resolveService(service, getResolveListener())
//                repo.get
//                Device.wifi.nsdManager.resolveService(service, getResolveListener())
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
            stopDiscovery()
        }

        override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {
            Log.e(TAG, "Discovery failed: Error code:$errorCode")
            stopDiscovery()
        }
    }

    fun stopDiscovery() {
        Log.e("Listeners", "stopDiscovery: ")
        wifiRepo.getNsdManager().stopServiceDiscovery(discoveryListener)
    }
}
