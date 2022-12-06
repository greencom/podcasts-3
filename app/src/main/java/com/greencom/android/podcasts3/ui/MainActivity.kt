package com.greencom.android.podcasts3.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.greencom.android.podcasts3.ui.app.App
import com.greencom.android.podcasts3.ui.common.screenbehaviors.screenorientation.LocalScreenOrientationBehaviorController
import com.greencom.android.podcasts3.ui.common.screenbehaviors.screenorientation.ScreenOrientationBehavior
import com.greencom.android.podcasts3.ui.common.screenbehaviors.screenorientation.ScreenOrientationBehaviorController
import com.greencom.android.podcasts3.utils.screenbehavior.DefaultScreenBehaviorController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            TransparentSystemBars()

            val screenOrientationBehaviorController =
                rememberScreenOrientationBehaviorController()

            CompositionLocalProvider(
                LocalScreenOrientationBehaviorController provides screenOrientationBehaviorController,
            ) {
                App()
            }
        }
    }
}

@Composable
private fun TransparentSystemBars() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons,
        )
        onDispose {}
    }
}

@Composable
private fun rememberScreenOrientationBehaviorController(): ScreenOrientationBehaviorController {
    return remember {
        DefaultScreenBehaviorController(
            defaultBehavior = ScreenOrientationBehavior(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
        )
    }
}
