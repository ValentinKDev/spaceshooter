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
        wifiRepo.isDeviceClient()?.let { eventMessage.handleAsClient() } ?: let { eventMessage.handleAsServer() }
    }
    private suspend fun EventMessage.handleAsClient() {
        when (this.type) {
            EventMessageType.SendServerName -> {
                Log.i(TAG, "handleEvent: ${EventMessageType.SendServerName.name}")
                toServerRepo.getChannel().info?.let { _serverInfo ->
                    val inetAddress = gson.fromJson( this.message, InetAddress::class.java )
                    _serverInfo.name = this.sender
                    wifiRepo.addVisibleDevice(inetAddress, this.sender)
                    wifiRepo.addConnectedDevice(inetAddress, this.sender)
                    Log.e(TAG, "handleAsClient: Visible Device list size ${Device.wifi.visibleDevices.value.size}", )
                }
            }
            EventMessageType.SendDeviceName -> {
                Log.i(TAG, "handleAsClient: ${EventMessageType.SendDeviceName}")
                Log.i(TAG, "handleAsClient: none")
            }
            EventMessageType.ReadyToPlay -> {
                Log.i(TAG, "handleAsClient: ${EventMessageType.ReadyToPlay.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToPlay)
                Log.i(TAG, "handleAsClient: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
            }
            EventMessageType.NotReadyToPlay -> {
                Log.i(TAG, "handleAsClient: ${EventMessageType.NotReadyToPlay.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.Waiting)
                Log.i(TAG, "handleAsClient: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
            }
            EventMessageType.InGame -> {
                Log.i(TAG, "handleAsClient: ${EventMessageType.InGame.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.InGame)
            }
            EventMessageType.SendProjectile -> {
                Log.i(TAG, "handleAsClient: ${EventMessageType.SendProjectile.name}")
                Log.i(TAG, "handleAsClient: ${this.message}")
                val projectileJson = this.message
                val projectile = Shoot.deserialize(projectileJson, gson)

                Log.i(TAG, "handleAsClient: shooter ip ${projectile.shooterIp}")
                Log.i(TAG, "handleAsClient: shooter from ${projectile.from}")
                Log.i(TAG, "handleAsClient: shooter type ${projectile.type}")
                Log.i(TAG, "handleAsClient: shooter offset ${projectile.offsetDp}")
                Log.i(TAG, "handleAsClient: shooter vector ${projectile.vector}")
                Log.i(TAG, "handleAsClient: shooter vector ${projectile.vector}")

                Device.event.incomingProjectile.emit( projectile.prepareReceivedProjectile() )
            }
            EventMessageType.Dead -> {
                Log.i(TAG, "handleAsClient: ${EventMessageType.Dead.name}")
                val looseInfo = gson.fromJson(this.message, LooseInfo::class.java)
                Log.i(TAG, "handleAsClient: shooterInetAddress ${looseInfo.shooterIp}")
                Log.i(TAG, "handleAsClient: sender ${this.sender}")
                Device.event.dead.emit( true )
            }
            EventMessageType.NewConnectedDevice -> TODO()
        }
    }
    suspend private fun EventMessage.handleAsServer() {
        when (this.type) {
            EventMessageType.NewConnectedDevice -> TODO()
            EventMessageType.SendDeviceName -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.SendDeviceName}")
                val inetAddressJson = this.message
                val inetAddress = gson.fromJson(inetAddressJson, InetAddress::class.java)
                toClientRepo.updateConnectedClientName(inetAddress, this.sender)
                //todo: write a factor function to redirect any type of messages to other clients
                toClientRepo.getConnectedClient(inetAddress)?.let { _client ->
                    Log.e(TAG, "handleEventAsServer: ip ${inetAddress}", )
                    wifiRepo.addVisibleDevice(inetAddress, _client.name)

                    sendServerNameToClient(_client)
                    val newConnectedDeviceEvent = EventMessage(EventMessageType.NewConnectedDevice, Device.data.name?:"", inetAddressJson)
                    sendEvent.toAll(toClientRepo.getClientsList(), newConnectedDeviceEvent, exception = _client )
                }
            }
            EventMessageType.NotReadyToPlay -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.NotReadyToPlay.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.Waiting)
                Log.i(TAG, "handleEventAsServer: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
            }
            EventMessageType.ReadyToPlay -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.ReadyToPlay.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToPlay)
                Log.i(TAG, "handleEventAsServer: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
            }
            EventMessageType.InGame -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.InGame.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.InGame)
            }
            EventMessageType.SendProjectile -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.SendProjectile.name}")
                val projectileJson = this.message
                val projectile = Shoot.deserialize(projectileJson, gson)
                Device.event.incomingProjectile.emit(projectile.prepareReceivedProjectile())
            }
            EventMessageType.Dead -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.Dead.name}")
                val looseInfo = gson.fromJson(this.message, LooseInfo::class.java)
                Log.i(TAG, "handleEventAsServer: shooterInetAddress ${looseInfo.shooterIp}")
                Log.i(TAG, "handleEventAsServer: sender ${this.sender}")
                Device.event.dead.emit( true )
            }
        }
    }
    suspend fun sendDeadUser(looseInfo: LooseInfo) = withContext(Dispatchers.IO) {
        Log.i(TAG, "sendDeadUser: ")
        val looseInfoJson = gson.toJson(looseInfo.shooterIp)
        genericFunction(EventMessageType.Dead, looseInfoJson)
    }
    suspend fun sendProjectile(shoot: Shoot) = withContext(Dispatchers.IO) {
        val projectileGson = shoot.serialize(gson)
        genericFunction(EventMessageType.SendProjectile, projectileGson)
    }
    suspend fun sendNotReadyToPlay() = withContext(Dispatchers.IO) {
        Log.i(TAG, "sendNotReadyToPlay: ")
        genericFunction(EventMessageType.NotReadyToPlay)
    }
    suspend fun sendReadyToPlay() = withContext(Dispatchers.IO) {
        Log.i(TAG, "sendReadyToPlay: Device.wifi.linkState.value ${Device.wifi.linkState.value}")
        genericFunction(EventMessageType.ReadyToPlay)
    }
    suspend fun sendDeviceInGame() = withContext(Dispatchers.IO) {
        val fTAG = "sendDeviceInGame"
        Log.i(TAG, "$fTAG: ")
        Device.data.name?.let { _name ->
//            val deviceNameMessage = EventMessage(EventMessageType.InGame.name, _name, message = "")
            val deviceNameMessage = EventMessage(EventMessageType.InGame, _name, message = "")
        }
    }
    fun sendNameToServer() {
        val fTAG = "sendNameToServer"
        Log.i(TAG, "$fTAG: ")

        Device.data.name?.let { _name ->
            toServerRepo.getChannel().info?.let { _info ->
                val inetAddressJson = gson.toJson(_info.socket.inetAddress)
                val deviceNameMessage = EventMessage(EventMessageType.SendDeviceName, _name, inetAddressJson)
                sendEvent.to(_info, deviceNameMessage)
            }
        }
    }
    private fun sendServerNameToClient(client: WifiClient) {
        Log.i(TAG, "sendServerNameToClient: ")
        Device.data.name?.let { _name ->
            val inetAddressJson = gson.toJson( wifiRepo.getLocalIp() )
//            val deviceNameMessage = EventMessage(EventMessageType.SendDeviceName.name, _name, inetAddressJson)
            val deviceNameMessage = EventMessage(EventMessageType.SendServerName, _name, inetAddressJson)
            sendEvent.to(client, deviceNameMessage)
        }
    }
    fun genericFunction(
        type: EventMessageType,
        strMessage: String = "generic",
    ) {
        Device.data.name?.let { _name ->
//            val evMsg = EventMessage(type.name, _name, strMessage)
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
//    fun handleEventFromServer(eventMessage: EventMessage) {
//        val fTAG = "handleEventFromServer"
//        when (EventMessageType.valueOf(eventMessage.type)) {
//            EventMessageType.SendDeviceName -> {
//                Log.i(TAG, "$fTAG: ${EventMessageType.SendDeviceName.name}")
//                toServerRepo.getChannel().info?.let { _serverInfo ->
//                    _serverInfo.name = eventMessage.sender
//                    wifiRepo.addVisibleDevice(_serverInfo.socket.localAddress, _serverInfo.name)
//                }
////                toServerRepo.
//            }
//            EventMessageType.ReadyToPlay -> {
//                Log.i(TAG, "$fTAG: ${EventMessageType.ReadyToPlay.name}")
//                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToPlay)
//                Log.i(TAG, "$fTAG: ${Device.wifi.visibleDevices.value.map { it.state }}")
//            }
//            EventMessageType.NotReadyToPlay -> {
//                Log.i(TAG, "$fTAG: ${EventMessageType.ReadyToPlay.name}")
//                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.Waiting)
//                Log.i(TAG, "$fTAG: ${Device.wifi.visibleDevices.value.map { it.state }}")
//            }
//            EventMessageType.InGame -> {
//                Log.i(TAG, "$fTAG: ${EventMessageType.InGame.name}")
//                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.InGame)
//            }
////            EventMessageType.NewConnectedDevice -> { wifiRepo.newVisibleDevice(eventMessage.message) }
////            EventMessageType.DisconnectDevice -> { wifiRepo.removeVisibleDevice(eventMessage.message) }
////            EventMessageType.SendGameData -> { newIncomingEvent(eventMessage) }
////            EventMessageType.ReadyToChooseSpaceShip -> TODO()
////            EventMessageType.Loose -> TODO()
////            EventMessageType.None -> TODO()
////            EventMessageType.NotReadyToChooseSpaceShip -> TODO()
////            EventMessageType.GoToSpaceShipMenuScreen -> TODO()
////            EventMessageType.ReadyToPlay -> TODO()
////            EventMessageType.NotReadyToPlay -> TODO()
////            EventMessageType.LaunchGame -> TODO()
//            else -> {}
//        }
//    }
//    fun handleEventFromClient(eventMessage: EventMessage, client: WifiClient) {
//        val fTAG = "handleEventFromClient"
//        when (EventMessageType.valueOf(eventMessage.type)) {
//            EventMessageType.NewConnectedDevice -> {}
//            EventMessageType.SendDeviceName -> {
//                Log.i(TAG, "$fTAG: ${EventMessageType.SendDeviceName}")
//                val inetAddressJson = eventMessage.message
//                val inetAddress = gson.fromJson(inetAddressJson, InetAddress::class.java)
//                toClientRepo.updateConnectedClientName(inetAddress, eventMessage.sender)
//                toClientRepo.getConnectedClient(inetAddress)?.let { _client ->
////                    wifiRepo.
//                    wifiRepo.addVisibleDevice(inetAddress, _client.name)
//                    sendServerNameToClient(_client)
//                    val newConnectedDeviceEvent = EventMessage(EventMessageType.NewConnectedDevice.name, Device.data.name?:"", inetAddressJson)
//                    sendEvent.toAll(toClientRepo.getClientsList(), newConnectedDeviceEvent, exception = _client )
//                }
//            }
//            EventMessageType.NotReadyToPlay -> {
//                Log.i(TAG, "$fTAG: ${EventMessageType.ReadyToPlay.name}")
//                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.Waiting)
//                Log.i(TAG, "$fTAG: ${Device.wifi.visibleDevices.value.map { it.state }}")
//            }
//            EventMessageType.ReadyToPlay -> {
//                Log.i(TAG, "$fTAG: ${EventMessageType.ReadyToPlay.name}")
//                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToPlay)
//                Log.i(TAG, "$fTAG: ${Device.wifi.visibleDevices.value.map { it.state }}")
//            }
//            EventMessageType.InGame -> {
//                Log.i(TAG, "$fTAG: ${EventMessageType.InGame.name}")
//                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.InGame)
//            }
//            EventMessageType.Test -> {
//                Log.e(TAG, "$fTAG: ${EventMessageType.InGame.name}")
//            }
////            EventMessageType.DisconnectDevice -> {
////                wifiRepo.getConnectedClient(eventMessage.sender)
////                wifiRepo.removeConnectedClient(client)
////                wifiRepo.closeChannelToClient(client)
////                val disconnectionEvent = EventMessage(EventMessageType.DisconnectDevice.name, client.name, "")
////                sendEvent.toAllClients(disconnectionEvent)
////            }
////            EventMessageType.SendGameData -> { sendEvent.toAllClients(eventMessage, exception = client) }
////            EventMessageType.ReadyToChooseSpaceShip -> TODO()
////            EventMessageType.Loose -> TODO()
////            EventMessageType.None -> TODO()
////            EventMessageType.NotReadyToChooseSpaceShip -> TODO()
////            EventMessageType.GoToSpaceShipMenuScreen -> TODO()
////            EventMessageType.ReadyToPlay -> TODO()
////            EventMessageType.NotReadyToPlay -> TODO()
////            EventMessageType.LaunchGame -> TODO()
//            else -> {}
//        }
//    }

