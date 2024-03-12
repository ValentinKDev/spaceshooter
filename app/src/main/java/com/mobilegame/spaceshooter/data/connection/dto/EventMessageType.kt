package com.mobilegame.spaceshooter.data.connection.dto


enum class EventMessageType {
    SendDeviceName,
    SendServerName,
    NewConnectedDevice,
//    DisconnectDevice,
//    SendGameData,
//    ReadyToChooseSpaceShip,
//    NotReadyToChooseSpaceShip,
//    GoToSpaceShipMenuScreen,
//    ReadyNotToPlay,
    InGame,
    ReadyToPlay,
    NotReadyToPlay,
    Dead,
//    LaunchGame,
//    Loose,
//    None,
    SendProjectile,
//    Test
}
