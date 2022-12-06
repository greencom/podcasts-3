package com.greencom.android.podcasts3.ui.common.screenbehaviors.screenorientation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun ScreenOrientation(orientation: Int) {
    val screenOrientationBehaviorController =
        LocalScreenOrientationBehaviorController.current

    DisposableEffect(screenOrientationBehaviorController, orientation) {
        val behavior = ScreenOrientationBehavior(orientation)
        screenOrientationBehaviorController.pushBehavior(behavior)

        onDispose {
            screenOrientationBehaviorController.popBehavior(behavior)
        }
    }
}
