package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen

import android.content.Context
import android.net.nsd.NsdManager
import android.util.Log
import com.mobilegame.spaceshooter.data.connection.wifi.WifiClient
import com.mobilegame.spaceshooter.data.connection.wifi.WifiLinkState
import com.mobilegame.spaceshooter.data.store.DataStoreKey
import com.mobilegame.spaceshooter.data.store.DataStoreNameProvider
import com.mobilegame.spaceshooter.data.store.DataStoreService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.PrintWriter
import java.net.Socket

class WifiConnectionInfo {
    val TAG = "WifiConnectionInfo"
    var deviceName = ""
    val networkSearchDiscoveryName = "SpaceShooterByWIFI"
    val type = "_SpaceShooterApp._tcp"
    var socket: Socket? = null
    var connectedClients: MutableList<WifiClient> = mutableListOf()
    lateinit var nsdManager: NsdManager
    var writer: PrintWriter? = null

    private val _visibleDeviceNameList = MutableStateFlow(listOf<String>())
    val visibleDeviceNameList: StateFlow<List<String>> = _visibleDeviceNameList.asStateFlow()
    fun newVisibleDevice(newDeviceName: String) {
        Log.e(TAG, "newVisibleDevice: \"$newDeviceName\"")
        val newList = visibleDeviceNameList.value.toMutableList()
        newList.add(newDeviceName)
        _visibleDeviceNameList.value = newList.toList()
    }
    fun removeVisibleDevice(deviceName: String) {
        val newList = visibleDeviceNameList.value.toMutableList()
        newList.remove(deviceName)
        _visibleDeviceNameList.value = newList.toList()
    }
    fun resetVisibleDevice() { _visibleDeviceNameList.value = listOf() }

    fun init(context: Context, name: String?) {
        Log.e(TAG, "init")
        Log.e(TAG, "init: $name")
        nsdManager = context.getSystemService(Context.NSD_SERVICE) as NsdManager
        Log.e(TAG, "init: $nsdManager")
        name?.let { deviceName = it }
    }

    private val plinkState = MutableStateFlow(WifiLinkState.NotConnected)
    val linkState: StateFlow<WifiLinkState> = plinkState.asStateFlow()
    fun updateLinkStateTo(newState: WifiLinkState) {
        plinkState.value = newState
    }
}