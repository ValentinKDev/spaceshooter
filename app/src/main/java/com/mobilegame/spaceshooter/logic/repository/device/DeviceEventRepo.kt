package com.mobilegame.spaceshooter.logic.repository.device

import android.util.Log
import com.google.gson.Gson
import com.mobilegame.spaceshooter.data.connection.dto.EventMessage
import com.mobilegame.spaceshooter.data.connection.dto.EventMessageType
import com.mobilegame.spaceshooter.data.connection.wifi.PreparationState
import com.mobilegame.spaceshooter.data.connection.wifi.SendEvent
import com.mobilegame.spaceshooter.data.connection.wifi.WifiLinkState
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiClient
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.GameResult
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.LooseInfo
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
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
    //todo : handle even trough listening to a shared flow event
    //todo : handle the when (type) by eventype and then by servers or client
    //todo : if devices send message to the server why keep open the client channel ?

//    suspend fun handleGameResult(gameResult: GameResult) {
//        when (gameResult) {
//            GameResult.VICTORY -> TODO()
//            GameResult.OnGoing -> TODO()
//        }
//    }

    suspend fun handleEvent(eventMessage: EventMessage) {
        Log.i(TAG, "handleEvent: ${eventMessage.type}")
        when (eventMessage.type) {
            EventMessageType.SendDeviceName -> {
                val inetAddressJson = eventMessage.message
                val inetAddress = gson.fromJson(inetAddressJson, InetAddress::class.java)
                toClientRepo.updateConnectedClientName(inetAddress, eventMessage.senderName)
                val clientChannel = Device.wifi.channels.withClients.find { it.info?.socket?.inetAddress == inetAddress }
                clientChannel?.info.let { _client ->
                    _client?.let {
                        wifiRepo.addVisibleDevice(inetAddress, eventMessage.senderName)
//                        sendServerNameToClient(it as WifiClient)
                        val newConnectedDeviceEvent = EventMessage(EventMessageType.NewConnectedDevice, eventMessage.senderName, inetAddressJson)
                        sendEvent.toAll(toClientRepo.getClientsList(), newConnectedDeviceEvent, exception = _client )
                    }
                }
                if (wifiRepo.noClientsRegisteredAt(inetAddress)) wifiRepo.updateLinkStateTo( WifiLinkState.RegisteredAsServer)
//                if (Device.wifi.visibleDevices.value.isEmpty()) wifiRepo.updateLinkStateTo( WifiLinkState.RegisteredAsServer)
                else wifiRepo.updateLinkStateTo(WifiLinkState.RegisteredAsServerAndClient)
                //todo: write a factor function to redirect any type of messages to other clients
            }
            EventMessageType.ReadyToChooseShip -> {
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToChooseShip)
            }
            EventMessageType.ReadyToPlay -> {
                val shipTypeName = eventMessage.message
                val shipType = ShipType.getType(shipTypeName)
                wifiRepo.changeVisibleDevicePreparationStateTo(state = PreparationState.ReadyToPlay, shipType = shipType)
            }
            EventMessageType.NotReadyToPlay -> { wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToChooseShip) }
            EventMessageType.NotReadyToChooseShip -> { wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.Waiting) }
            EventMessageType.SendProjectile -> {
//                Log.v(TAG, "handleEvent: start")
                val projectileSerialized = eventMessage.message
//                Log.v(TAG, "handleEvent: projectileJson ${projectileSerialized}")
                val projectile = Shoot.deserialize(projectileSerialized, gson)
//                Log.e(TAG, "handleEvent: projectile ${projectile.from} ${projectile.type} ")
                Device.event.projectileFlow.emit( projectile.prepareReceivedProjectile() )
            }
            EventMessageType.Dead -> {
                val looseInfoJson: String = eventMessage.message
                val looseInfo: LooseInfo = LooseInfo.fromJson(looseInfoJson)

                val clientChannel = Device.wifi.channels.withClients.find { it.info?.socket?.inetAddress == looseInfo.deadPlayerIp }
                clientChannel?.info.let { _client ->
                    _client?.let {
                        genericEventMessage(type = EventMessageType.Dead, strMessage = looseInfoJson)?.let {eventMessage ->
                            sendEvent.toAll(toClientRepo.getClientsList(), eventMessage, exception = _client )
                        }
                        Device.event.gameResult.emit(GameResult.VICTORY)
                    }
                }
            }
            EventMessageType.NewConnectedDevice -> {}
            EventMessageType.InGame -> {}
        }
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
//                        sendServerNameToClient(it as WifiClient)
                        val newConnectedDeviceEvent = EventMessage(EventMessageType.NewConnectedDevice, eventMessage.senderName, inetAddressJson)
                        sendEvent.toAll(toClientRepo.getClientsList(), newConnectedDeviceEvent, exception = _client )
                    }
                }
                //todo: write a factor function to redirect any type of messages to other clients
            }
            EventMessageType.NotReadyToChooseShip -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.NotReadyToChooseShip.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.Waiting)
                Log.i(TAG, "handleEventAsServer: visible devices state ${Device.wifi.visibleDevices.value.map { it.state.name }}")
            }
            EventMessageType.NotReadyToPlay -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.NotReadyToPlay}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToChooseShip)
//                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.Waiting)
                Log.i(TAG, "handleEventAsServer: visible devices state ${Device.wifi.visibleDevices.value.map { it.state.name }}")
            }
            EventMessageType.ReadyToPlay -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.ReadyToPlay}")
                val shipTypeName = eventMessage.message
                val shipType = ShipType.getType(shipTypeName)
                wifiRepo.changeVisibleDevicePreparationStateTo(state = PreparationState.ReadyToPlay, shipType = shipType)
//                Device.event.eventTypeFlow.emit(EventMessageType.ReadyToPlay)
                Log.i(TAG, "handleEventAsServer: visible devices state ${Device.wifi.visibleDevices.value.map { Pair(it.shipType?.info?.name, it.state.name) }}")
//                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToPlay)
//                Device.event.eventTypeFlow.emit(EventMessageType.ReadyToPlay)
//                Log.i(TAG, "handleEventAsServer: visible devices state ${Device.wifi.visibleDevices.value.map { it.state }}")
            }
            EventMessageType.ReadyToChooseShip -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.ReadyToChooseShip.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToChooseShip)
                Log.i(TAG, "handleEventAsServer: visible devices state ${Device.wifi.visibleDevices.value.map { it.state.name }}")
            }
            EventMessageType.InGame -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.InGame.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.InGame)
            }
            EventMessageType.SendProjectile -> {
                Log.i(TAG, "handleEventAsServer: ${EventMessageType.SendProjectile.name}")
//                val projectileJson = eventMessage.message
//                val projectile = Shoot.deserialize(projectileJson, gson)
//                Log.i(TAG, "handleEventAsClient: shoot type ${projectile.type}")
//                Log.i(TAG, "handleEventAsClient: shoot type ${projectile.type.id}")
//                Device.event.projectileFlow.emit( projectile.prepareReceivedProjectile() )
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
                        Device.event.gameResult.emit(GameResult.VICTORY)
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
                Log.i(TAG, "handleEventAsClient: visible devices state ${Device.wifi.visibleDevices.value.map { it.state.name }}")
            }
            EventMessageType.ReadyToPlay -> {
                Log.i(TAG, "handleEventAsClient: ${EventMessageType.ReadyToPlay}")
//                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToPlay, shipType = getthetype by the name of the ship in the strmessage)
                val shipTypeName = eventMessage.message
                val shipType = ShipType.getType(shipTypeName)
                wifiRepo.changeVisibleDevicePreparationStateTo(state = PreparationState.ReadyToPlay, shipType = shipType)
//                Device.event.eventTypeFlow.emit(EventMessageType.ReadyToPlay)
                Log.i(TAG, "handleEventAsClient: visible devices state ${Device.wifi.visibleDevices.value.map { Pair(it.shipType?.info?.name, it.state.name) }}")
            }
            EventMessageType.NotReadyToChooseShip -> {
                Log.i(TAG, "handleEventAsClient: ${EventMessageType.NotReadyToChooseShip.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.Waiting)
                Log.i(TAG, "handleEventAsClient: visible devices state ${Device.wifi.visibleDevices.value.map { it.state.name }}")
            }
            EventMessageType.NotReadyToPlay -> {
                Log.i(TAG, "handleEventAsClient: ${EventMessageType.NotReadyToPlay}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.ReadyToChooseShip)
//                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.Waiting)
                Log.i(TAG, "handleEventAsClient: visible devices state ${Device.wifi.visibleDevices.value.map { it.state.name }}")
            }
            EventMessageType.InGame -> {
                Log.i(TAG, "handleEventAsClient: ${EventMessageType.InGame.name}")
                wifiRepo.changeVisibleDevicePreparationStateTo(PreparationState.InGame)
            }
            EventMessageType.SendProjectile -> {
//                Log.i(TAG, "handleEventAsClient: ${EventMessageType.SendProjectile.name}")
//                val projectileJson = eventMessage.message
//                val projectile = Shoot.deserialize(projectileJson, gson)
//                Log.i(TAG, "handleEventAsClient: shoot type ${projectile.type}")
//                Device.event.projectileFlow.emit( projectile.prepareReceivedProjectile() )
            }
            EventMessageType.NewConnectedDevice -> TODO()
            EventMessageType.Dead -> TODO()
        }
    }
    fun sendDeadUser(looseInfo: LooseInfo) {
        val looseInfoGson = looseInfo.toJson()
        genericFunction(type = EventMessageType.Dead, looseInfoGson)
    }
    suspend fun sendProjectile(shoot: Shoot) = withContext(Dispatchers.IO) {
        Log.i(TAG, "sendProjectile: ")
        val projectileSerialized = shoot.serialize(gson)
        genericFunction(type = EventMessageType.SendProjectile, strMessage = projectileSerialized)
    }
//    suspend fun sendReadyToPlay() = withContext(Dispatchers.IO) {
    suspend fun sendReadyToPlay(withShip: String) = withContext(Dispatchers.IO) {
        Log.i(TAG, "sendReadyToPlay: ")
//        genericFunction(type = EventMessageType.ReadyToPlay)
        genericFunction(type = EventMessageType.ReadyToPlay, strMessage = withShip)
    }
    suspend fun sendNotReadyToPlay() = withContext(Dispatchers.IO) {
        Log.i(TAG, "sendNotReadyToPlay: ")
        genericFunction(type = EventMessageType.NotReadyToPlay)
    }
    suspend fun sendNotReadyToChooseShip() = withContext(Dispatchers.IO) {
        Log.i(TAG, "sendNotReadyToChooseShip: ")
        genericFunction(type = EventMessageType.NotReadyToChooseShip)
    }
    suspend fun sendReadyToChooseShip() = withContext(Dispatchers.IO) {
        Log.i(TAG, "sendReadyToChooseShip: ")
        genericFunction(type = EventMessageType.ReadyToChooseShip)
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
//        Log.d(TAG, "genericFunction: client ${wifiRepo.isDeviceClient()} / server ${wifiRepo.isDeviceServer()}")
        genericEventMessage(type, strMessage)?.let { eventMessage ->
                toServerRepo.getChannel().info?.let { _info ->
                    sendEvent.to(_info, eventMessage)
                } ?: let {
                    Log.e( TAG, "genericFunction: ERROR to'ServerRepo.getChannel().info is ${toServerRepo.getChannel().info}" )
                }
        }
    }
    private fun genericEventMessage(
        type: EventMessageType,
        strMessage: String,
    ): EventMessage? = Device.data.name?.let { name ->  EventMessage(type, name, strMessage) }
        ?: let { Log.e(TAG, "genericEventMessage: ERROR Device.data.name is ${Device.data.name}"); null }
}