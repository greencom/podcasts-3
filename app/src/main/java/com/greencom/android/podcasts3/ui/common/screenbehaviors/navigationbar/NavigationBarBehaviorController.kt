package com.greencom.android.podcasts3.ui.common.screenbehaviors.navigationbar

import androidx.compose.runtime.staticCompositionLocalOf
import com.greencom.android.podcasts3.utils.screenbehavior.NoOpScreenBehaviorController
import com.greencom.android.podcasts3.utils.screenbehavior.ScreenBehaviorController

typealias NavigationBarBehaviorController = ScreenBehaviorController<NavigationBarBehavior>

val LocalNavigationBarBehaviorController =
    staticCompositionLocalOf<NavigationBarBehaviorController> {
        NoOpScreenBehaviorController(
            defaultBehavior = NavigationBarBehavior.Hidden(isAnimated = false)
        )
    }
