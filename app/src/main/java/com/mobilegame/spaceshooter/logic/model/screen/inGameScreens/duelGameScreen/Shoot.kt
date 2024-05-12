package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.mobilegame.spaceshooter.data.connection.dto.ShootSerializable
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle.MunitionsType
import com.mobilegame.spaceshooter.utils.TypeListFloat
import com.mobilegame.spaceshooter.utils.extensions.getXForY
import com.mobilegame.spaceshooter.utils.extensions.getYForX
import com.mobilegame.spaceshooter.utils.extensions.invert
import com.mobilegame.spaceshooter.utils.extensions.invertX
import com.mobilegame.spaceshooter.utils.extensions.isInBoundsOf
import com.mobilegame.spaceshooter.utils.extensions.toDpOffset
import com.mobilegame.spaceshooter.utils.extensions.toOffset
import com.mobilegame.spaceshooter.utils.extensions.toPair
import java.net.InetAddress

data class Shoot(
    val type: ShipType,
    val from: MunitionsType = MunitionsType.UserProjectile,
    val shooterIp: InetAddress = InetAddress.getByName("0.0.0.0"),
//    var vector: DpSize = DpSize.Zero,
    val vector: DpOffset = DpOffset.Zero,
    val particularBehavior: Int,
    val damage: Float,
    //todo : instead of xRatio and yRatio make a stop at Y
    var xRatio: Float, //from left to right
    var yRatio: Float, //from top to bottom
    var offsetDp: DpOffset = DpOffset.Zero,
//    val laserOnUserScreen: Pair<DpOffset, DpOffset> = DpOffset.Unspecified.toPair(),
//    val laserOnOpponentScreen: Pair<DpOffset, DpOffset> = DpOffset.Unspecified.toPair(),
    val laserOnUserScreen: Pair<Offset, Offset> = Offset.Zero.toPair(),
    val laserOnOpponentScreen: Pair<Offset, Offset> = Offset.Zero.toPair(),
) {
    private val TAG = "Shoot"
    //todo : add some kind of size of behavior number related to the shiptype
    fun updateDpOffset() {
        offsetDp = DpOffset(
            x = offsetDp.x + vector.x,
            y = offsetDp.y - vector.y,
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
            x = this.offsetDp.x - this.vector.x,
            y = this.offsetDp.y + this.vector.y,
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
            x = this.offsetDp.x + this.vector.x,
            y = this.offsetDp.y - this.vector.y,
        )
    )
    fun prepareShootToSendAway(): Shoot = Shoot (
        type = this.type,
        from = MunitionsType.EnemiesProjectile,
        shooterIp = this.shooterIp,
        vector = this.vector.invert(),
        particularBehavior = this.particularBehavior,
        damage = this.damage,
        xRatio = Device.metrics.getXRatioWithDp(this.offsetDp.x),
        yRatio = this.yRatio,
        offsetDp = this.offsetDp,
        laserOnUserScreen = this.laserOnUserScreen,
        laserOnOpponentScreen = this.laserOnOpponentScreen
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
        offsetDp = this.offsetDp.invertX(this.xRatio),
        laserOnUserScreen = this.laserOnUserScreen,
        laserOnOpponentScreen = this.laserOnOpponentScreen
    )
    fun serialize(gson: Gson): String {
        val preSerializedShoot = ShootSerializable (
            typeName = this.type.info.name,
            from = this.from,
            shooterIp = this.shooterIp,
            vector = gson.toJson(this.vector),
            particularBehavior = this.particularBehavior,
            xRatio = this.xRatio,
            yRatio = this.yRatio,
            damage = this.damage,
            offsetDp = gson.toJson(listOf(this.offsetDp.x.value, this.offsetDp.y.value), TypeListFloat),
//            offsetDp = ,
            laserOnUser = gson.toJson(listOf<Float>(
                this.laserOnUserScreen.first.x,
                this.laserOnUserScreen.first.y,
                this.laserOnUserScreen.second.x,
                this.laserOnUserScreen.second.y,
            ), TypeListFloat),
            laserOnOpponent = gson.toJson(listOf<Float>(
                this.laserOnOpponentScreen.first.x,
                this.laserOnOpponentScreen.first.y,
                this.laserOnOpponentScreen.second.x,
                this.laserOnOpponentScreen.second.y,
            ), TypeListFloat),
        )
        return gson.toJson(preSerializedShoot)
    }

    companion object {
        private val TAG = "Shoot"
        fun deserialize(shootJson: String, gson: Gson = Gson()): Shoot {
//            Log.i(TAG, "deserialize: shootJson $shootJson")
            val preSerializedShoot = gson.fromJson(shootJson, ShootSerializable::class.java)
//            Log.i(TAG, "deserialize: preSerializedShoot $preSerializedShoot")
            val type: ShipType = ShipType.getType(name = preSerializedShoot.typeName)
//            Log.=(TAG, "deserialize: typename ${type.info.name}")
            val vector = gson.fromJson(preSerializedShoot.vector, DpOffset::class.java)
//            Log.i(TAG, "deserialize: vector: ${vector}")
            val dpOffsetFloatList: List<Float> = gson.fromJson(preSerializedShoot.offsetDp, TypeListFloat)
//            Log.i(TAG, "deserialize: dpOffsetJson : ${dpOffsetJson}")
            val offsetDp = DpOffset(dpOffsetFloatList[0].dp, dpOffsetFloatList[1].dp)
//            Log.i(TAG, "deserialize: offsetDp : ${offsetDp}")
            val onUserScreenPairList: List<Float> = gson.fromJson(preSerializedShoot.laserOnUser, TypeListFloat )
//            Log.i(TAG, "deserialize: onUserScreenPairList: ${onUserScreenPairList}")
            val onUserScreenPair: Pair<Offset, Offset> = Pair(
                    Offset( x = onUserScreenPairList[0], y = onUserScreenPairList[1]),
                    Offset( x = onUserScreenPairList[2], y = onUserScreenPairList[3])
            )
//            Log.i(TAG, "deserialize: onUserScreenPair: ${onUserScreenPair}")
//            Log.i(TAG, "deserialize: onOpponentScreenPairStr: ${preSerializedShoot.laserOnOpponent}")
            val onOpponentScreenPairList: List<Float> = gson.fromJson(preSerializedShoot.laserOnOpponent, TypeListFloat )
//            Log.i(TAG, "deserialize: onOpponentScreenPairList: ${onOpponentScreenPairList}")
            val onOpponentScreenPair: Pair<Offset, Offset> = Pair(
                Offset( x = onOpponentScreenPairList[0], y = onOpponentScreenPairList[1]),
                Offset( x = onOpponentScreenPairList[2], y = onOpponentScreenPairList[3])
            )
//            Log.i(TAG, "deserialize: onOpponentScreenPair: ${onOpponentScreenPair}")
            val shoot = Shoot(
                type = type,
                from = preSerializedShoot.from,
                shooterIp = preSerializedShoot.shooterIp,
                vector = vector,
                particularBehavior = preSerializedShoot.particularBehavior,
                damage = preSerializedShoot.damage,
                xRatio = preSerializedShoot.xRatio,
                yRatio = preSerializedShoot.yRatio,
                offsetDp = offsetDp,
                laserOnUserScreen = onUserScreenPair,
                laserOnOpponentScreen = onOpponentScreenPair,
            )
//            Log.e(TAG, "deserialize: shoot $shoot")
            return shoot
        }
        fun newFromUser(type: ShipType, vm: MotionsViewModel, behavior: Int = 1, damage: Float = 1F): Shoot {
            var dpOffsetPairOnScreen = Offset.Zero.toPair()
            var dpOffsetPairOutScreen = Offset.Zero.toPair()
            var vector: DpOffset = vm.getShootVector()
            if (type == ShipType.Lasery) {
                Log.e(TAG, "newFromUser : \n\n\ntest", )
                val start: DpOffset = vm.getShipTopCenter()
                var laserVector: Offset = vector.toOffset()
                dpOffsetPairOnScreen = getLaserOnUserScreen(
                    start,
//                    vm.getShipTopCenter(),
                    vm.displaySize,
                    laserVector,
                    vector
                )
                if (dpOffsetPairOnScreen.second isInBoundsOf vm.displaySize ) {
                    Log.i(TAG, "newFromUser: laser is getting out")
//                    xRatio = Device.metrics.getXRatioWithDp(this.offsetDp.x),
//                    vector = vector.invertX(Device.metrics.getXRatioWithDp(start.x),)
                    dpOffsetPairOutScreen = getLaserOutScreen(dpOffsetPairOnScreen, vector)
                }
            }
            return Shoot(
                type = type,
                from = MunitionsType.UserProjectile,
                shooterIp = Device.wifi.inetAddress,
                vector = vector,
                particularBehavior = behavior,
                damage = type.info.damage,
                xRatio = Float.MIN_VALUE,
                yRatio = Device.metrics.getYRatioWithDp(vm.getShipTopCenter().y),
                offsetDp = vm.getShipTopCenter(),
                laserOnUserScreen = dpOffsetPairOnScreen,
                laserOnOpponentScreen = dpOffsetPairOutScreen,
            )
        }
        private fun getLaserOutScreen(laserOnScreen: Pair<Offset, Offset>, vector: DpOffset): Pair<Offset, Offset> {
            val xRatio = Device.metrics.getXRatioWithPx(laserOnScreen.second.x)
            Log.e(TAG, "getLaserOutScreen: ratio $xRatio")
            val start: Offset = laserOnScreen.second.invertX(xRatio)
            val startDp = start.toDpOffset()
            val end = DpOffset(startDp.x + vector.x, startDp.y - vector.y)
            val maxY = Device.metrics.sizeDp.height
            val xDp: Dp = getXForY(startDp, end, maxY)
            val laserEnd: DpOffset = DpOffset(xDp, maxY)
            val laserOut: Pair<Offset, Offset> = Pair(start, laserEnd.toOffset())

            return laserOut
        }

        private fun getLaserOnUserScreen(shipTopCenter: DpOffset, displaySize: Size, vectorPx: Offset, vector: DpOffset): Pair<Offset, Offset> {
            val start: DpOffset = shipTopCenter
            val end: DpOffset = DpOffset( x = start.x + vector.x, y = start.y - vector.y)
            val xDp: Float = getXForY(Offset(start.x.value, start.y.value), Offset(end.x.value, end.y.value), 0.1F)
            val laserEndDp = DpOffset(xDp.dp, 0.dp)
            val laserEnd = laserEndDp.toOffset()
            val laser = Pair(start.toOffset(), laserEndDp.toOffset())
            Log.e(TAG, "getLaseOnUserScreen: on Screen $laser")

            return laser
        }

        val UNDEFINED = Shoot(
            type = ShipType.Square,
            from = MunitionsType.UserProjectile,
//            shooterIp = null,
            vector = DpOffset.Unspecified,
            particularBehavior = 0,
            damage = Float.MIN_VALUE,
            xRatio = Float.MIN_VALUE,
            yRatio = Float.MIN_VALUE,
            offsetDp = DpOffset.Unspecified
        )
    }
}