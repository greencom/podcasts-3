package com.greencom.android.podcasts3.ui

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.greencom.android.podcasts3.ui.app.App
import com.greencom.android.podcasts3.ui.common.screenbehaviors.screenorientation.LocalScreenOrientationBehaviorController
import com.greencom.android.podcasts3.ui.common.screenbehaviors.screenorientation.ScreenOrientationBehavior
import com.greencom.android.podcasts3.ui.common.screenbehaviors.screenorientation.ScreenOrientationBehaviorController
import com.greencom.android.podcasts3.ui.theme.AppTheme
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

            RequestedActivityOrientation(
                activity = this,
                screenOrientationBehaviorController = screenOrientationBehaviorController,
            )

            CompositionLocalProvider(
                LocalScreenOrientationBehaviorController provides screenOrientationBehaviorController,
            ) {
                AppTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        App()
                    }
                }
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
            defaultBehavior = ScreenOrientationBehavior(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        )
    }
}

@Composable
private fun RequestedActivityOrientation(
    activity: Activity,
    screenOrientationBehaviorController: ScreenOrientationBehaviorController,
) {
    val orientationBehavior by screenOrientationBehaviorController.currentBehavior
    LaunchedEffect(orientationBehavior.orientation) {
        if (activity.requestedOrientation != orientationBehavior.orientation) {
            activity.requestedOrientation = orientationBehavior.orientation
        }
    }
}
