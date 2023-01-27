package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen

import android.app.Application
import android.content.ContentValues.TAG
import android.net.wifi.p2p.WifiP2pDevice
import android.net.wifi.p2p.WifiP2pManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.channel
import com.mobilegame.spaceshooter.data.store.DataStoreNameProvider
import com.mobilegame.spaceshooter.data.store.DataStoreService
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.registerDevice.RegisterDeviceViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.WifiScreenUI
import com.mobilegame.spaceshooter.managerW
import com.mobilegame.spaceshooter.utils.analyze.eLog
import kotlinx.coroutines.launch


class WifiScreenViewModel(application: Application): AndroidViewModel(application) {
    val registerVM = RegisterDeviceViewModel(application, Screens.WifiScreen)
    val ui = WifiScreenUI()
    private val userNameDataStore = DataStoreService.createDeviceName(application)
    var userName: String? = null
    init {
        viewModelScope.launch() {
            userName = userNameDataStore.getString(DataStoreNameProvider.DeviceName.key)
//            userName = "TEST"
        }
    }
    fun goDiscovery() {
        viewModelScope.launch() {
//            WifiManager().
        }
        managerW.discoverPeers(channel, object : WifiP2pManager.ActionListener {

            override fun onSuccess() {
                eLog("onSuccess", "SUCCESS")
                // Code for when the discovery initiation is successful goes here.
                // No services have actually been discovered yet, so this method
                // can often be left blank. Code for peer discovery goes in the
                // onReceive method, detailed below.
            }

            override fun onFailure(reasonCode: Int) {
                eLog("onFailure", "FAILURE")
                // Code for when the discovery initiation fails goes here.
                // Alert the user that something went wrong.
            }
        })

    }
    fun fetch() {
        val peers = mutableListOf<WifiP2pDevice>()

        val peerListListener = WifiP2pManager.PeerListListener { peerList ->
            val refreshedPeers = peerList.deviceList
            if (refreshedPeers != peers) {
                peers.clear()
                peers.addAll(refreshedPeers)

                // If an AdapterView is backed by this data, notify it
                // of the change. For instance, if you have a ListView of
//                 available peers, trigger an update.
//                (listAdapter as WiFiPeerListAdapter).notifyDataSetChanged()

//                 Perform any other updates needed based on the new list of
//                 peers connected to the Wi-Fi P2P network.
            }

            if (peers.isEmpty()) {
                Log.d("l", "No devices found")
                return@PeerListListener
            } else {
                Log.d(TAG, "Device found")
            }
        }
    }
}

//class Fetcher():
//private lateinit var manager: WifiP2pManager
//private lateinit var channel: WifiP2pManager.Channel
//private lateinit var receiver: WiFiDirectBroadcastReceiver
//
//class WifiTruc(val intent: IntentFilter): Activity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        manager = getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
//        channel = manager.initialize(this, mainLooper, null)
//    }
//
//    /** register the BroadcastReceiver with the intent values to be matched  */
//    public override fun onResume() {
//        super.onResume()
////        receiver = WiFiDirectBroadcastReceiver(manager, channel, this)
////        registerReceiver(receiver, intent)
//    }
//
//    public override fun onPause() {
//        super.onPause()
//        unregisterReceiver(receiver)
//    }
//}
