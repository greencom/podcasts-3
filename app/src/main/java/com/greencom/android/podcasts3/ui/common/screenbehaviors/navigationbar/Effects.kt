package com.greencom.android.podcasts3.ui.common.screenbehaviors.navigationbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun NavigationBarBehavior(isVisible: Boolean, isAnimated: Boolean = true) {
    val navigationBarBehaviorController =
        LocalNavigationBarBehaviorController.current

    DisposableEffect(navigationBarBehaviorController, isVisible, isAnimated) {
        val behavior = if (isVisible) {
            NavigationBarBehavior.Visible(isAnimated)
        } else {
            NavigationBarBehavior.Hidden(isAnimated)
        }
        navigationBarBehaviorController.pushBehavior(behavior)

        onDispose {
            navigationBarBehaviorController.popBehavior(behavior)
        }
    }
}
