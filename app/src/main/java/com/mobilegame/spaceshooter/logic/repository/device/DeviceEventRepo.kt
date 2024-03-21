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
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.LooseInfo
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
        Log.i(TAG, "handleEvent: ")
        wifiRepo.isDeviceClient()?.let {
            handleEventAsClient(eventMessage)
        } ?: handleEventAsServer(eventMessage)
    }
    private suspend fun handleEventAsServer(eventMessage: EventMessage) {
        when (eventMessage.type) {
            EventMessageType.NewConnectedDevice -> TODO()
            EventMessageType.SendDeviceName -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.SendDeviceName}")
                val inetAddressJson = eventMessage.message
                val inetAddress = gson.fromJson(inetAddressJson, InetAddress::class.java)
                toClientRepo.updateConnectedClientName(inetAddress, eventMessage.senderName)
                Log.i(TAG, "handleEventAsServer: handle ip $inetAddress")
                val clientChannel = Device.wifi.channels.withClients.find { it.info?.socket?.inetAddress == inetAddress }
                clientChannel?.info.let { _client ->
                    _client?.let {
                        wifiRepo.addVisibleDevice(inetAddress, eventMessage.senderName)
                        sendServerNameToClient(it as WifiClient)
                        val newConnectedDeviceEvent = EventMessage(EventMessageType.NewConnectedDevice, eventMessage.senderName, inetAddressJson)
                        sendEvent.toAll(toClientRepo.getClientsList(), newConnectedDeviceEvent, exception = _client )
                    }
                }
                //todo: write a factor function to redirect any type of messages to other clients
            }
            EventMessageType.NotReadyToChooseShip -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.NotReadyToChooseShip.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.Waiting)
                Log.i(TAG, "handleEventAsServer: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
            }
            EventMessageType.NotReadyToPlay -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.NotReadyToPlay}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToChooseShip)
//                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.Waiting)
                Log.i(TAG, "handleEventAsServer: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
            }
            EventMessageType.ReadyToPlay -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.ReadyToPlay}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToPlay)
                Log.i(TAG, "handleEventAsServer: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
            }
            EventMessageType.ReadyToChooseShip -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.ReadyToChooseShip.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToChooseShip)
                Log.i(TAG, "handleEventAsServer: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
            }
            EventMessageType.InGame -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.InGame.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.InGame)
            }
            EventMessageType.SendProjectile -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.SendProjectile.name}")
                val projectileJson = eventMessage.message
                val projectile = Shoot.deserialize(projectileJson, gson)
                Log.i(TAG, "handleEventAsClient: shoot type ${projectile.type}")
                Log.i(TAG, "handleEventAsClient: shoot type ${projectile.type.name}")
                Device.event.projectileFlow.emit( projectile.prepareReceivedProjectile() )
            }
            EventMessageType.Dead -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.Dead}")
                val looseInfoJson: String = eventMessage.message
                val looseInfo: LooseInfo = LooseInfo.fromJson(looseInfoJson)

                val clientChannel = Device.wifi.channels.withClients.find { it.info?.socket?.inetAddress == looseInfo.deadPlayerIp }
                clientChannel?.info.let { _client ->
                    _client?.let {
                        genericEventMessage(type = EventMessageType.Dead, strMessage = looseInfoJson)?.let {eventMessage ->
                            sendEvent.toAll(toClientRepo.getClientsList(), eventMessage, exception = _client )
                        }
                    }
                }
            }
        }
    }
    private suspend fun handleEventAsClient(eventMessage: EventMessage) {
        Log.i(TAG, "handleEventAsClient handle event as a Client")
        when (eventMessage.type) {
            EventMessageType.SendDeviceName -> {
                Log.i(TAG, "handleEventAsClient: ${EventMessageType.SendDeviceName.name}")
                val inetAddressJson = eventMessage.message
                val inetAddress = gson.fromJson(inetAddressJson, InetAddress::class.java)
                wifiRepo.addVisibleDevice(inetAddress, eventMessage.senderName)
            }
            EventMessageType.ReadyToChooseShip -> {
                Log.i(TAG, "handleEventAsClient: ${EventMessageType.ReadyToChooseShip.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToChooseShip)
                Log.i(TAG, "handleEventAsClient: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
            }
            EventMessageType.ReadyToPlay -> {
                Log.i(TAG, "handleEventAsClient: ${EventMessageType.ReadyToPlay}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToPlay)
                Log.i(TAG, "handleEventAsClient: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
            }
            EventMessageType.NotReadyToChooseShip -> {
                Log.i(TAG, "handleEventAsClient: ${EventMessageType.NotReadyToChooseShip.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.Waiting)
                Log.i(TAG, "handleEventAsClient: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
            }
            EventMessageType.NotReadyToPlay -> {
                Log.i(TAG, "handleEventAsClient: ${EventMessageType.NotReadyToPlay}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToChooseShip)
//                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.Waiting)
                Log.i(TAG, "handleEventAsClient: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
            }
            EventMessageType.InGame -> {
                Log.i(TAG, "handleEventAsClient: ${EventMessageType.InGame.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.InGame)
            }
            EventMessageType.SendProjectile -> {
                Log.i(TAG, "handleEventAsClient: ${EventMessageType.SendProjectile.name}")
                val projectileJson = eventMessage.message
                val projectile = Shoot.deserialize(projectileJson, gson)
                Log.i(TAG, "handleEventAsClient: shoot type ${projectile.type}")
                Device.event.projectileFlow.emit( projectile.prepareReceivedProjectile() )
            }
            EventMessageType.NewConnectedDevice -> TODO()
            EventMessageType.Dead -> TODO()
        }
    }
    fun sendDeadUser(looseInfo: LooseInfo) {
        val looseInfoGson = looseInfo.toJson()
        genericFunction(EventMessageType.Dead, looseInfoGson)
    }
    suspend fun sendProjectile(shoot: Shoot) = withContext(Dispatchers.IO) {
        val projectileGson = shoot.serialize(gson)
        genericFunction(EventMessageType.SendProjectile, projectileGson)
    }
    suspend fun sendReadyToPlay() = withContext(Dispatchers.IO) {
        Log.i(TAG, "sendReadyToPlay: ")
        genericFunction(EventMessageType.ReadyToPlay)
    }
    suspend fun sendNotReadyToPlay() = withContext(Dispatchers.IO) {
        Log.i(TAG, "sendNotReadyToPlay: ")
        genericFunction(EventMessageType.NotReadyToPlay)
    }
    suspend fun sendNotReadyToChooseShip() = withContext(Dispatchers.IO) {
        Log.i(TAG, "sendNotReadyToChooseShip: ")
        genericFunction(EventMessageType.NotReadyToChooseShip)
    }
    suspend fun sendReadyToChooseShip() = withContext(Dispatchers.IO) {
        Log.i(TAG, "sendReadyToChooseShip: ")
        genericFunction(EventMessageType.ReadyToChooseShip)
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
                val inetAddressJson = gson.toJson(_info.socket.localAddress)
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
    private fun genericFunction(
        type: EventMessageType,
        strMessage: String = "generic",
    ) {
        genericEventMessage(type, strMessage)?.let { eventMessage ->
            wifiRepo.isDeviceClient()?.let { Log.i(TAG, "to server")
                toServerRepo.getChannel().info?.let { _info ->
                    sendEvent.to(_info, eventMessage)
                }
            } ?: let { Log.i(TAG, "to client")
                val infoList: List<WifiInfoService> = toClientRepo.withClientChannels().map { it.info!! }
                sendEvent.toAll(infoList, eventMessage)
            }
        }
    }
    private fun genericEventMessage(
        type: EventMessageType,
        strMessage: String = "generic",
    ): EventMessage? = Device.data.name?.let { EventMessage(type, it, strMessage) } ?: let { null }
}