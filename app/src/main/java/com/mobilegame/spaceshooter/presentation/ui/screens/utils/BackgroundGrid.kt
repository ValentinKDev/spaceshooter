package com.mobilegame.spaceshooter.presentation.ui.screens.utils

//@Composable
//fun BackgroundGrid(ui: BackgroundUI) {
//    val dotDpSize: Dp = remember { ui.dotSizeDp }
//    val dpOffsetList: List<DpOffset> = remember { ui.dpOffsetList }
//    val drawGrid = remember { ui.drawGrid }.collectAsState()
////    val bitmap = remember { ui.bitmap }
//    val timerStart = Calendar.getInstance().timeInMillis
////    val view = LocalView.current
////    val context = LocalContext.current
//
//    LaunchedEffect(key1 = "") {
//
//        drawGrid.value?.let { if (it) { ui.drawGrid.value = false } }
//        if (ui.dpOffsetList.isNotEmpty()) {
//            ui.drawGrid.value = true
////            ui.createBitmap(view, context)
//        }
//    }
//
////    bitmap?.let {
////        Image(
////            modifier = Modifier
////                .onGloballyPositioned { layout ->
////                    Log.i("backgroundGrid", "BackgroundGrid: ${layout.size}")
////                }
////            ,
////            bitmap = it.asImageBitmap(),
////            contentDescription = "test"
////        )
////    }
//
//    drawGrid.value?.let { grid ->
//        if (grid) {
//        Log.e("BackgroundGrid", "Grid CREATION: launch / relaunch")
//        Box(
//            Modifier
//                .fillMaxSize()
//                .background(Color.Black)
//        ) {
//            dpOffsetList.forEach {
//                Box(
//                    Modifier
//                        .offset(x = it.x, y = it.y)
//                        .background(MyColor.grayDark8)
//                        .size(dotDpSize)
//                )
//            }
//        }
//        val timerStop = Calendar.getInstance().timeInMillis
//        Log.e("BackgroundGrid", "Grid CREATION: time creation ${timerStop - timerStart }")
//    } }
//}