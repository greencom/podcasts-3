package com.greencom.android.podcasts3.ui.common.screenbehaviors.screenorientation

import android.content.pm.ActivityInfo
import androidx.compose.runtime.staticCompositionLocalOf
import com.greencom.android.podcasts3.utils.screenbehavior.NoOpScreenBehaviorController
import com.greencom.android.podcasts3.utils.screenbehavior.ScreenBehaviorController

typealias ScreenOrientationBehaviorController = ScreenBehaviorController<ScreenOrientationBehavior>

val LocalScreenOrientationBehaviorController =
    staticCompositionLocalOf<ScreenOrientationBehaviorController> {
        NoOpScreenBehaviorController(
            defaultBehavior = ScreenOrientationBehavior(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
        )
    }
