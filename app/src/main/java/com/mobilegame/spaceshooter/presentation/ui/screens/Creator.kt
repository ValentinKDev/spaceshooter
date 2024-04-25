package com.mobilegame.spaceshooter.presentation.ui.screens

import android.app.Application
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.PressureHandler
import com.mobilegame.spaceshooter.logic.uiHandler.template.TemplateUI
import com.mobilegame.spaceshooter.utils.analyze.eLog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Creator(navigator: Navigator ,vm: CreatorViewModel = viewModel()) {
    LaunchedEffect(true) {
        eLog("Creator", "Creator launch")
    }

}

class CreatorViewModel(application: Application): AndroidViewModel(application) {
//    val vmPressure = PressureHandler()
    val templateUI = TemplateUI()
}
//interface Shape {
//    fun createOutline(size: Size, density: Density): Outline
//}
//
//class CustomShape( ) : Shape {
//    override fun createOutline(
//        size: Size,
//        layoutDirection: LayoutDirection,
//        density: Density
//    ): Outline {
//        val path = Path().apply {
//            moveTo(size.width / 2f, 0f)
//            lineTo(size.width, size.height)
//            lineTo(0f, size.height)
//            close()
//        }
//        return Outline.Generic(path)
//    }
//}

//class Test(val sides: Int, val rotation: Float = 0f) : Shape {
//    override fun createOutline(
//        size: Size,
//        layoutDirection: LayoutDirection,
//        density: Density
//    ): Outline {
//        return Outline.Generic(
//            Path().apply {
//                moveTo(size.width / 2f, 0f)
//                lineTo(size.width, size.height)
//                lineTo(0f, size.height)
//                close()
//            }
//        )
//    }
//}