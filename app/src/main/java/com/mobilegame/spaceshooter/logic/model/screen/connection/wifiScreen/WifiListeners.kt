package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen

import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.util.Log
import com.mobilegame.spaceshooter.data.connection.wifi.WifiLinkState
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.repository.device.DeviceEventRepo
import com.mobilegame.spaceshooter.logic.repository.device.DeviceWifiRepo
import com.mobilegame.spaceshooter.logic.repository.connection.WifiChannelsWithClientRepo
import com.mobilegame.spaceshooter.logic.repository.connection.WifiChannelsWithServerRepo
import com.mobilegame.spaceshooter.utils.extensions.containsNot

class WifiListeners() {
    val wifiRepo = DeviceWifiRepo()
    val toServerRepo = WifiChannelsWithServerRepo()
    val toClientRepo = WifiChannelsWithClientRepo()
    val eventRepo = DeviceEventRepo()

    fun getResolveListener(): NsdManager.ResolveListener = object : NsdManager.ResolveListener {
        val TAG = "resolveListener"

        override fun onResolveFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
            // Called when the resolve fails. Use the error code to debug.
            Log.e(TAG, "ERROR Resolve failed: $errorCode for ${serviceInfo.serviceName}")
        }

        override fun onServiceResolved(serviceInfo: NsdServiceInfo) {
            Log.i(TAG, "onServiceResolved: ${serviceInfo.serviceName.contains(Device.wifi.networkSearchDiscoveryName)}")
//            if ( serviceInfo.serviceName.equals(Device.wifi.networkSearchDiscoveryName)
            if ( serviceInfo.serviceName.contains(Device.wifi.networkSearchDiscoveryName)) {
//                && serviceInfo.host != wifiRepo.getLocalIp()) {
//                && serviceInfo.serviceName.containsNot(Device.data.name!!)) {
                Log.d( TAG, "Service found: ${serviceInfo.serviceName} ${serviceInfo.port} ${serviceInfo.host} ${serviceInfo.serviceType} ${serviceInfo.attributes}" )
                toServerRepo.addServer(serviceInfo)
                DeviceEventRepo().sendNameToServer()
//                wifiRepo.setLinkState(WifiLinkState.RegisteredAsServerAndClient)
            }
        }
    }

    fun stopDiscovery() {
        Log.e("Listeners", "stopDiscovery: ")
//        wifiRepo.getNsdManager().stopServiceDiscovery(discoveryListener)
        wifiRepo.getClientNsdManager().stopServiceDiscovery(discoveryListener)
//        wifiRepo.getNsdManager().unregisterService(discoveryListener)
//        wifiRepo.getNsdManager()
    }
    var discoveryListener = getADiscoveryListener()
        get() {
            field = getADiscoveryListener()
            return field
        }
    private fun getADiscoveryListener(): NsdManager.DiscoveryListener = object : NsdManager.DiscoveryListener {
//    val discoveryListener: NsdManager.DiscoveryListener = object : NsdManager.DiscoveryListener {

        val TAG = "discoveryListener"

        // Called as soon as service discovery begins.
        override fun onDiscoveryStarted(regType: String) {
            Log.d(TAG, "Service discovery started")
        }

        override fun onServiceFound(service: NsdServiceInfo) {
            Log.d(TAG, "Service found: ${service.serviceName} ${service.port} ${service.host} ${service.serviceType} ${service.attributes}")
//            Log.d(TAG, "Service found ${service.serviceName}")
//            Log.d(TAG, "Service found ${service.attributes}")

            // A service was found! Do something with it.
//            if (service.serviceName.equals(Device.wifi.networkSearchDiscoveryName) ) {
//            wifiRepo.getNsdManager().resolveService(service, getResolveListener())
            if ( service.serviceName.containsNot(Device.data.name!!)) {
                wifiRepo.getClientNsdManager().resolveService(service, getResolveListener())
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

}
