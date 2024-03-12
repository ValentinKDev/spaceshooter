package com.mobilegame.spaceshooter.logic.repository.device

import android.util.Log
import com.google.gson.Gson
import com.mobilegame.spaceshooter.data.connection.dto.EventMessage
import com.mobilegame.spaceshooter.data.connection.dto.EventMessageType
import com.mobilegame.spaceshooter.data.connection.wifi.PreparationState
import com.mobilegame.spaceshooter.data.connection.wifi.SendEvent
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiClient
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiInfoService
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import com.mobilegame.spaceshooter.logic.repository.connection.WifiChannelsWithClientRepo
import com.mobilegame.spaceshooter.logic.repository.connection.WifiChannelsWithServerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetAddress


class DeviceEventRepo() {
    val TAG = "DeviceEventRepo"
    val gson = Gson()
    val wifiRepo = DeviceWifiRepo()
    val toServerRepo = WifiChannelsWithServerRepo()
    val toClientRepo = WifiChannelsWithClientRepo()
    private val sendEvent = SendEvent()
    suspend fun handleEvent(eventMessage: EventMessage) {
        val fTAG = "handleEvent"
        Log.i(TAG, fTAG)
        wifiRepo.isDeviceClient()?.let {
            Log.i(TAG, "$fTAG handle event as a Client")
//            when (EventMessageType.valueOf(eventMessage.type)) {
            when (eventMessage.type) {
                EventMessageType.SendDeviceName -> {
                    Log.i(TAG, "$fTAG: ${EventMessageType.SendDeviceName.name}")
                    val inetAddressJson = eventMessage.message
                    val inetAddress = gson.fromJson(inetAddressJson, InetAddress::class.java)
                    wifiRepo.addVisibleDevice(inetAddress, eventMessage.senderName)
                }
                EventMessageType.ReadyToPlay -> {
                    Log.i(TAG, "$fTAG: ${EventMessageType.ReadyToPlay.name}")
                    wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToPlay)
                    Log.i(TAG, "$fTAG: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
//                    Log.i(TAG, "$fTAG: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
//                    Log.i(TAG, "$fTAG: visible devices state ${Device.wifi.visibleDevices.map { it.map { it.state } }}")
                }
                EventMessageType.NotReadyToPlay -> {
                    Log.i(TAG, "$fTAG: ${EventMessageType.NotReadyToPlay.name}")
                    wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.Waiting)
                    Log.i(TAG, "$fTAG: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
//                    Log.i(TAG, "$fTAG: visible devices state ${Device.wifi.visibleDevices.map { it.map { it.state } }}")
                }
                EventMessageType.InGame -> {
                    Log.i(TAG, "$fTAG: ${EventMessageType.InGame.name}")
                    wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.InGame)
                }
                EventMessageType.SendProjectile -> {
                    Log.i(TAG, "handleEvent: ${EventMessageType.SendProjectile.name}")
                    val projectileJson = eventMessage.message
                    val projectile = Shoot.deserialize(projectileJson, gson)
                    Device.event.incomingProjectile.emit( projectile.prepareReceivedProjectile() )
                }
                EventMessageType.NewConnectedDevice -> TODO()
            }
        } ?: let {
            Log.i(TAG, "$fTAG handle event as a Server")
//            when (EventMessageType.valueOf(eventMessage.type)) {
            when (eventMessage.type) {
                EventMessageType.NewConnectedDevice -> TODO()
                EventMessageType.SendDeviceName -> {
                    Log.i(TAG, "$fTAG: ${EventMessageType.SendDeviceName}")
                    val inetAddressJson = eventMessage.message
                    val inetAddress = gson.fromJson(inetAddressJson, InetAddress::class.java)
                    toClientRepo.updateConnectedClientName(inetAddress, eventMessage.senderName)
                    Log.i(TAG, "handleEvent: handle ip $inetAddress")
                    val test = Device.wifi.channels.withClients.find { it.info?.socket?.inetAddress == inetAddress }
                    test?.info.let {_client ->
                        _client?.let {
                            wifiRepo.addVisibleDevice(inetAddress, eventMessage.senderName)
                            sendServerNameToClient(it as WifiClient)
                            val newConnectedDeviceEvent = EventMessage(EventMessageType.NewConnectedDevice, eventMessage.senderName, inetAddressJson)
                            sendEvent.toAll(toClientRepo.getClientsList(), newConnectedDeviceEvent, exception = _client )
                        }
                    }
                    //todo: write a factor function to redirect any type of messages to other clients
//                    toClientRepo.getConnectedClient(inetAddress)?.let { _client ->
//                        wifiRepo.addVisibleDevice(inetAddress, _client.name)
//                        sendServerNameToClient(_client)
//                        val newConnectedDeviceEvent = EventMessage(EventMessageType.NewConnectedDevice, Device.data.name?:"", inetAddressJson)
//                        sendEvent.toAll(toClientRepo.getClientsList(), newConnectedDeviceEvent, exception = _client )
//                    }
                }
                EventMessageType.NotReadyToPlay -> {
                    Log.i(TAG, "$fTAG: ${EventMessageType.NotReadyToPlay.name}")
                    wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.Waiting)
                    Log.i(TAG, "$fTAG: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
                }
                EventMessageType.ReadyToPlay -> {
                    Log.i(TAG, "$fTAG: ${EventMessageType.ReadyToPlay.name}")
                    wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToPlay)
                    Log.i(TAG, "$fTAG: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
                }
                EventMessageType.InGame -> {
                    Log.i(TAG, "$fTAG: ${EventMessageType.InGame.name}")
                    wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.InGame)
                }
                EventMessageType.SendProjectile -> {
                    Log.i(TAG, "handleEvent: ${EventMessageType.SendProjectile.name}")
                    val projectileJson = eventMessage.message
                    val projectile = Shoot.deserialize(projectileJson, gson)
                    Device.event.incomingProjectile.emit( projectile.prepareReceivedProjectile() )
                }
            }
        }
    }
    suspend fun sendProjectile(shoot: Shoot) = withContext(Dispatchers.IO) {
        val projectileGson = shoot.serialize(gson)
        genericFunction(EventMessageType.SendProjectile, projectileGson)
    }
    suspend fun sendNotReadyToPlay() = withContext(Dispatchers.IO) {
        val fTAG = "sendNotReadyToPlay"
        Log.i(TAG, "$fTAG: ")
        genericFunction(EventMessageType.NotReadyToPlay)
    }
    suspend fun sendReadyToPlay() = withContext(Dispatchers.IO) {
        val fTAG = "sendReadyToPlay"
        Log.i(TAG, "$fTAG: ")
        Log.i(TAG, "$fTAG: Device.wifi.linkState.value ${Device.wifi.linkState.value}")
        genericFunction(EventMessageType.ReadyToPlay)
    }
    suspend fun sendDeviceInGame() = withContext(Dispatchers.IO) {
        val fTAG = "sendDeviceInGame"
        Log.i(TAG, "$fTAG: ")
        Device.data.name?.let { _name ->
            val deviceNameMessage = EventMessage(EventMessageType.InGame, _name, message = "")
        }
    }
//    fun sendNameToServer(localIp: InetAddress) {
    fun sendNameToServer() {
        Log.i(TAG, "sendNameToServer: ")

        Device.data.name?.let { _name ->
            toServerRepo.getChannel().info?.let { _info ->
//                val inetAddressJson = gson.toJson(Device.wifi.inetAddress)
//                val inetAddressJson = gson.toJson(_info.socket.inetAddress)
                val inetAddressJson = gson.toJson(_info.socket.localAddress)
//                val inetAddressJson = gson.toJson(localIp)
                val deviceNameMessage = EventMessage(EventMessageType.SendDeviceName, _name, inetAddressJson)
                sendEvent.to(_info, deviceNameMessage)
            }
        }
    }
    private fun sendServerNameToClient(client: WifiClient) {
        Log.i(TAG, "sendServerNameToClient: ")
        Device.data.name?.let { _name ->
            val inetAddress = wifiRepo.getLocalIp()
            Log.i(TAG, "sendServerNameToClient: client ip $inetAddress")
            val inetAddressJson = gson.toJson(inetAddress)
            val deviceNameMessage = EventMessage(EventMessageType.SendDeviceName, _name, inetAddressJson)
            sendEvent.to(client, deviceNameMessage)
        }
    }
    fun genericFunction(
        type: EventMessageType,
        strMessage: String = "generic",
    ) {
        Device.data.name?.let { _name ->
            val evMsg = EventMessage(type, _name, strMessage)
            wifiRepo.isDeviceClient()?.let { Log.i(TAG, "to server")
                toServerRepo.getChannel().info?.let { _info ->
                    sendEvent.to(_info, evMsg)
                }
            } ?: let { Log.i(TAG, "to client")
                val infoList: List<WifiInfoService> = toClientRepo.withClientChannels().map { it.info!! }
                sendEvent.toAll(infoList, evMsg)
            }
        }
    }
}