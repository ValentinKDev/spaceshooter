package com.mobilegame.spaceshooter.logic.repository.device

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
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
import org.json.JSONStringer
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
                    toServerRepo.getChannel().info?.let { _serverInfo ->
                        _serverInfo.name = eventMessage.sender
                        wifiRepo.addVisibleDevice(_serverInfo.socket.localAddress, _serverInfo.name)
                    }
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
                    Log.i(TAG, "handleEvent: ${eventMessage.message}")
                    val projectileJson = eventMessage.message
                    val projectile = Shoot.deserialize(projectileJson, gson)
//                    val projectile: Shoot = Shoot.receivedFromEnemy(gson.fromJson(projectileJson, Shoot::class.java))

                    Log.i(TAG, "handleEvent: shooter ip ${projectile.shooterIp}")
                    Log.i(TAG, "handleEvent: shooter from ${projectile.from}")
                    Log.i(TAG, "handleEvent: shooter type ${projectile.type}")
                    Log.i(TAG, "handleEvent: shooter offset ${projectile.offsetDp}")
                    Log.i(TAG, "handleEvent: shooter vector ${projectile.vector}")
                    Log.i(TAG, "handleEvent: shooter vector ${projectile.vector}")

                    Device.event.incomingProjectile.emit( projectile.prepareReceivedProjectile() )
//                    Device.event.incomingProjectile.emit( projectile )
//                    Device.event.incomingProjectile.value = projectile
//                    val test = async {}

//                    CompletableFuture.runAsync() {
//                        Device.event.incomingProjectile.emit(projectile)
//
//                    }
                    //todo inverser le vecteur
                    //todo inverser la position
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
                    toClientRepo.updateConnectedClientName(inetAddress, eventMessage.sender)
                    //todo: write a factor function to redirect any type of messages to other clients
                    toClientRepo.getConnectedClient(inetAddress)?.let { _client ->
//                    wifiRepo.
                        wifiRepo.addVisibleDevice(inetAddress, _client.name)
                        sendServerNameToClient(_client)
//                        val newConnectedDeviceEvent = EventMessage(EventMessageType.NewConnectedDevice.name, Device.data.name?:"", inetAddressJson)
                        val newConnectedDeviceEvent = EventMessage(EventMessageType.NewConnectedDevice, Device.data.name?:"", inetAddressJson)
                        sendEvent.toAll(toClientRepo.getClientsList(), newConnectedDeviceEvent, exception = _client )
                    }
                }
                EventMessageType.NotReadyToPlay -> {
                    Log.i(TAG, "$fTAG: ${EventMessageType.NotReadyToPlay.name}")
                    wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.Waiting)
                    Log.i(TAG, "$fTAG: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
//                    Log.i(TAG, "$fTAG: visible devices state ${Device.wifi.visibleDevices.map { it.map { it.state } }}")
                }
                EventMessageType.ReadyToPlay -> {
                    Log.i(TAG, "$fTAG: ${EventMessageType.ReadyToPlay.name}")
                    wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToPlay)
//                    Log.i(TAG, "$fTAG: ${Device.wifi.visibleDevices.value.map { it.state }}")
//                    Log.i(TAG, "$fTAG: visible devices state ${Device.wifi.visibleDevices.map { it.map { it.state } }}")
                    Log.i(TAG, "$fTAG: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
                }
                EventMessageType.InGame -> {
                    Log.i(TAG, "$fTAG: ${EventMessageType.InGame.name}")
                    wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.InGame)
                }
//                EventMessageType.Test -> {
//                    Log.e(TAG, "$fTAG: ${EventMessageType.InGame.name}")
//                }
//                else -> {}
                EventMessageType.SendProjectile -> {
                    Log.i(TAG, "handleEvent: ${EventMessageType.SendProjectile.name}")
                }
            }
        }
    }
    suspend fun sentProjectile(shoot: Shoot) = withContext(Dispatchers.IO) {
//        val projectileJsonData = gson.toJson(shoot)
//        val projectileJsonData = JsonObject().getAsJsonObject()
//        val projectileJsonData = JSONStringer().:
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
//                val deviceNameMessage = EventMessage(EventMessageType.SendDeviceName.name, _name, inetAddressJson)
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
            val deviceNameMessage = EventMessage(EventMessageType.SendDeviceName, _name, inetAddressJson)
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

