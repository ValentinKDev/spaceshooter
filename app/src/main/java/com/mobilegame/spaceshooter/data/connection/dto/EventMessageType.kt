package com.mobilegame.spaceshooter.data.connection.dto


enum class EventMessageType {
    SendDeviceName,
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
//    LaunchGame,
//    Loose,
//    None,
    SendProjectile,
//    Test
}
