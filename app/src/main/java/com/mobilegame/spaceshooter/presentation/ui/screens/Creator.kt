package com.mobilegame.spaceshooter.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator

@Composable
fun Creator(navigator: Navigator,vm: MainScreenViewModel = viewModel()) {
    Column {
//        Text(
//            text = "WRITE YOUR NAME",
//            color = MaterialTheme.colorScheme.background,
//            fontSize = 20.sp,
//            modifier = Modifier.fillMaxWidth()
//        )
//        TextTruc()
    }
//    Template(
//        type = TemplatesType.WithoutHeadBand,
//        type = TemplatesType.WithHeadBand,
//        header = {},
//        headBand = {}
//    ) { }
}



@Composable
fun TextTruc() {
    val constraints = ConstraintSet {
        val greenBox = createRefFor("greenBox")
        val redBox = createRefFor("redBox")
        val yellowBox = createRefFor("yellowBox")

//        constrain(greenBox) {
//            top.linkTo(parent.top)
//            start.linkTo(parent.start)
//            width = Dimension.value(100.dp)
//            height = Dimension.percent(0.1F)
//        }
//        constrain(yellowBox) {
//            top.linkTo(greenBox.bottom)
//            start.linkTo(parent.start)
//            end.linkTo(parent.end)
//            bottom.linkTo(redBox.bottom)
//            width = Dimension.fillToConstraints
//            height = Dimension.fillToConstraints
//        }
        constrain(redBox) {
            top.linkTo(parent.top)
//            start.linkTo(parent.start)
//            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
    }
    ConstraintLayout(constraints, Modifier.fillMaxSize()) {
        Box(
            Modifier
                .background(Color.Green)
                .layoutId("greenBox")
        )
        Box(
            Modifier
                .background(Color.Red)
                .layoutId("redBox")
        ) {
            Box(modifier = Modifier.size(40.dp))
        }
        Box(
            Modifier
                .background(Color.Yellow)
                .layoutId("yellowBox")
        )
    }
}
