package com.mobilegame.spaceshooter.data.connection.dto


enum class EventMessageType {
    SendDeviceName,
    NewConnectedDevice,
    DisconnectDevice,
    SendGameData,
    ReadyToChooseSpaceShip,
    NotReadyToChooseSpaceShip,
    GoToSpaceShipMenuScreen,
    ReadyToPlay,
    NotReadyToPlay,
    LaunchGame,
    Loose,
    None
}
