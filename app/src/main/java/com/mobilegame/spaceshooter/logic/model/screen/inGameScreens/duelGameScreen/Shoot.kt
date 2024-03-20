package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.mobilegame.spaceshooter.data.connection.dto.ShootSerializable
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle.MunitionsType
import java.net.InetAddress

data class Shoot(
    val type: ShipType,
    val from: MunitionsType = MunitionsType.UserProjectile,
    val shooterIp: InetAddress,
    var vector: Size = Size.Zero,
    val particularBehavior: Int,
    val damage: Float,
    var xRatio: Float, //from left to right
    var yRatio: Float, //from top to bottom
    var offsetDp: DpOffset = DpOffset.Zero,
) {
    private val TAG = "Shoot"
    //todo : add some kind of size of behavior number related to the shiptype
    fun updateDpOffset() {
        offsetDp = DpOffset(
            x = offsetDp.x + vector.width.dp,
            y = offsetDp.y - vector.height.dp,
        )
    }
    fun getShootPrecedentDpOffset() = Shoot(
        type = this.type,
        from = this.from,
        shooterIp = this.shooterIp,
        vector = this.vector,
        particularBehavior = this.particularBehavior,
        damage = this.damage,
        xRatio = this.xRatio,
        yRatio = this.yRatio,
        offsetDp = DpOffset(
            x = offsetDp.x - vector.width.dp,
            y = offsetDp.y + vector.height.dp,
        )
    )
    fun getShootNextDpOffset() = Shoot(
        type = this.type,
        from = this.from,
        shooterIp = this.shooterIp,
        vector = this.vector,
        particularBehavior = this.particularBehavior,
        damage = this.damage,
        xRatio = this.xRatio,
        yRatio = this.yRatio,
        offsetDp = DpOffset(
            x = offsetDp.x + vector.width.dp,
            y = offsetDp.y - vector.height.dp,
        )
    )
    fun serialize(gson: Gson): String {
        val preSerializedShoot = ShootSerializable (
            type = this.type.name,
            from = this.from,
            shooterIp = this.shooterIp,
            vector = gson.toJson(this.vector),
            particularBehavior = this.particularBehavior,
            xRatio = this.xRatio,
            yRatio = this.yRatio,
            damage = this.damage,
            offsetDp = gson.toJson(this.offsetDp),
        )
        return gson.toJson(preSerializedShoot)
    }
    fun prepareShootToSendAway(): Shoot = Shoot (
        type = this.type,
        from = MunitionsType.EnemiesProjectile,
        shooterIp = this.shooterIp,
        vector = this.vector.invert(),
        particularBehavior = this.particularBehavior,
        damage = this.damage,
        xRatio = Device.metrics.getXRatio(this.offsetDp.x.value),
        yRatio = this.yRatio,
        offsetDp = this.offsetDp
    )
    fun prepareReceivedProjectile(): Shoot = Shoot (
        type = this.type,
        from = this.from,
        shooterIp = this.shooterIp,
        vector = this.vector,
        particularBehavior = this.particularBehavior,
        damage = this.damage,
        xRatio = this.xRatio,
        yRatio = this.yRatio,
        offsetDp = this.offsetDp.invertX(this.xRatio)
    )
    private fun Size.invert(): Size = Size(this.width * -1F, this.height * -1F)
    private fun DpOffset.invertX(xRatio: Float): DpOffset = DpOffset( (Device.metrics.sizeDp.width.value * (1F - xRatio)).dp, this.y )

    companion object {
        fun deserialize(shootJson: String, gson: Gson = Gson()): Shoot {
            val preSerializedShoot = gson.fromJson(shootJson, ShootSerializable::class.java)
            val type: ShipType = when (preSerializedShoot.type) {
                ShipType.Circle.name -> ShipType.Circle
                ShipType.Square.name -> ShipType.Square
                else -> ShipType.Square
            }
            val vector = gson.fromJson(preSerializedShoot.vector, Size::class.java)
            val offsetDp = gson.fromJson(preSerializedShoot.offsetDp, DpOffset::class.java)
            return Shoot(
                type = type,
                from = preSerializedShoot.from,
                shooterIp = preSerializedShoot.shooterIp,
                vector = vector,
                particularBehavior = preSerializedShoot.particularBehavior,
                damage = preSerializedShoot.damage,
                xRatio = preSerializedShoot.xRatio,
                yRatio = preSerializedShoot.yRatio,
                offsetDp = offsetDp
            )
        }
        fun newFromUser(type: ShipType, vm: MotionsViewModel, behavior: Int = 1, damage: Float = 1F): Shoot = Shoot(
            type = type,
            from = MunitionsType.UserProjectile,
            shooterIp = Device.wifi.inetAddress,
            vector = vm.getShootVector(),
            particularBehavior = behavior,
            damage = type.info.damage,
            xRatio = Float.NaN,
            yRatio = Device.metrics.getYRatio(vm.getShipTopCenter().y.value),
            offsetDp = vm.getShipTopCenter(),
        )
//        val UNDEFINED = Shoot(
//            type = ShipType.Square,
//            from = MunitionsType.UserProjectile,
//            shooterIp = null,
//            vector = Size.Unspecified,
//            particularBehavior = 0,
//            damage = Float.NaN,
//            xRatio = Float.NaN,
//            yRatio = Float.NaN,
//            offsetDp = DpOffset.Unspecified
//        )
    }
}