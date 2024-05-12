package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.SpaceWarGameViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.ShipView

@Composable
fun DisplayShipView(vm: SpaceWarGameViewModel) {
    val position by remember { vm.shipVM.motionVM.shipPosition }.collectAsState()
    val motion by remember { vm.shipVM.motionVM.motion }.collectAsState()
    val speed by remember { vm.shipVM.motionVM.speedMagnitude }.collectAsState()
    val lifeRatio by  remember {vm.shipVM.lifeVM.lifeRatio }.collectAsState()
    val angleHitAnimation by remember { vm.hitAnimVM.angleHitAnimation }.collectAsState()
    val chargingAnimation by remember { vm.shipVM.ammoVM.chargingAnimation }.collectAsState()
//    val maga by remember { vm.shipVM.ammoVM.chargingAnimation }.collectAsState()
    val magazineSize by remember { vm.shipVM.ammoVM.magazineSize }.collectAsState()

    val angle by animateFloatAsState(
        targetValue = vm.shipVM.motionVM.getTargetAngle(motion, speed),
        animationSpec = tween(600),
        label = ""
    )

    Box(
        Modifier
            .wrapContentSize()
            .offset(position.x, position.y)
            .rotate(angle + angleHitAnimation)
    ) {
        ShipView(
            type = vm.userShipType,
            shipViewSizeBox = vm.ui.sizes.shipViewBox,
            magazine = magazineSize,
            lifeRatio = lifeRatio,
            visibleCharging = chargingAnimation,
        )
    }
}