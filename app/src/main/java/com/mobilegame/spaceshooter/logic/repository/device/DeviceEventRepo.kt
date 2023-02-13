package com.mobilegame.spaceshooter.logic.repository.device

import android.util.Log
import com.google.gson.Gson
import com.mobilegame.spaceshooter.data.connection.dto.EventMessage
import com.mobilegame.spaceshooter.data.connection.dto.EventMessageType
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiClient
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.data.connection.wifi.SendEvent
import com.mobilegame.spaceshooter.logic.repository.connection.WifiChannelsWithClientRepo
import com.mobilegame.spaceshooter.logic.repository.connection.WifiChannelsWithServerRepo
import java.net.InetAddress

class DeviceEventRepo() {
    val TAG = "DeviceEventRepo"
    val gson = Gson()
    val wifiRepo = DeviceWifiRepo()
    val toServerRepo = WifiChannelsWithServerRepo()
    val toClientRepo = WifiChannelsWithClientRepo()
    val sendEvent = SendEvent()
    fun newIncomingEvent(newEvent: EventMessage) {
        Device.event.incoming.value = newEvent
    }
    fun handleEventFromServer(eventMessage: EventMessage) {
        when (EventMessageType.valueOf(eventMessage.type)) {
            EventMessageType.SendDeviceName -> {
                Log.i(TAG, "handleEventFromServer: ${EventMessageType.SendDeviceName.name}")
                toServerRepo.getChannel().info?.let { _serverInfo ->
                    _serverInfo.name = eventMessage.sender
                    wifiRepo.addVisibleDevice(_serverInfo.socket.localAddress, _serverInfo.name)
                }
            }
//            EventMessageType.NewConnectedDevice -> { wifiRepo.newVisibleDevice(eventMessage.message) }
//            EventMessageType.DisconnectDevice -> { wifiRepo.removeVisibleDevice(eventMessage.message) }
//            EventMessageType.SendGameData -> { newIncomingEvent(eventMessage) }
//            EventMessageType.ReadyToChooseSpaceShip -> TODO()
//            EventMessageType.Loose -> TODO()
//            EventMessageType.None -> TODO()
//            EventMessageType.NotReadyToChooseSpaceShip -> TODO()
//            EventMessageType.GoToSpaceShipMenuScreen -> TODO()
//            EventMessageType.ReadyToPlay -> TODO()
//            EventMessageType.NotReadyToPlay -> TODO()
//            EventMessageType.LaunchGame -> TODO()
            else -> {}
        }
    }
    fun handleEventFromClient(eventMessage: EventMessage, client: WifiClient) {
        when (EventMessageType.valueOf(eventMessage.type)) {
            EventMessageType.SendDeviceName -> {
                Log.i(TAG, "handleEventFromClient: ${EventMessageType.SendDeviceName}")
                val inetAddressJson = eventMessage.message
                val inetAddress = gson.fromJson(inetAddressJson, InetAddress::class.java)
                toClientRepo.updateConnectedClientName(inetAddress, eventMessage.sender)
                toClientRepo.getConnectedClient(inetAddress)?.let { _client ->
                    wifiRepo.addVisibleDevice(inetAddress, _client.name)
                    sendServerNameToClient(_client)
                    val newConnectedDeviceEvent = EventMessage(EventMessageType.NewConnectedDevice.name, Device.data.name?:"", inetAddressJson)
                    sendEvent.toAll(toClientRepo.getClientsList(), newConnectedDeviceEvent, exception = _client )
                }
            }
            EventMessageType.NewConnectedDevice -> {}
//            EventMessageType.DisconnectDevice -> {
//                wifiRepo.getConnectedClient(eventMessage.sender)
//                wifiRepo.removeConnectedClient(client)
//                wifiRepo.closeChannelToClient(client)
//                val disconnectionEvent = EventMessage(EventMessageType.DisconnectDevice.name, client.name, "")
//                sendEvent.toAllClients(disconnectionEvent)
//            }
//            EventMessageType.SendGameData -> { sendEvent.toAllClients(eventMessage, exception = client) }
//            EventMessageType.ReadyToChooseSpaceShip -> TODO()
//            EventMessageType.Loose -> TODO()
//            EventMessageType.None -> TODO()
//            EventMessageType.NotReadyToChooseSpaceShip -> TODO()
//            EventMessageType.GoToSpaceShipMenuScreen -> TODO()
//            EventMessageType.ReadyToPlay -> TODO()
//            EventMessageType.NotReadyToPlay -> TODO()
//            EventMessageType.LaunchGame -> TODO()
            else -> {}
        }
    }

    fun sendNameToServer(host: InetAddress) {
        Log.i(TAG, "sendNameToServer: ")
        Device.data.name?.let { _name -> toServerRepo.getChannel().info?.let { _info ->
            val inetAddressJson = gson.toJson(host)
            val deviceNameMessage = EventMessage(EventMessageType.SendDeviceName.name, _name, inetAddressJson)
            sendEvent.to(_info, deviceNameMessage)
        }}
    }
    private fun sendServerNameToClient(client: WifiClient) {
        Log.i(TAG, "sendServerNameToClient: ")
        Device.data.name?.let { _name ->
            val inetAddressJson = gson.toJson( wifiRepo.getLocalIp() )
            val deviceNameMessage = EventMessage(EventMessageType.SendDeviceName.name, _name, inetAddressJson)
            sendEvent.to(client, deviceNameMessage)
        }
    }
}