package com.greencom.android.podcasts3.ui.app

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.greencom.android.podcasts3.ui.app.components.AppNavigation
import com.greencom.android.podcasts3.ui.app.components.AppNavigationBar
import com.greencom.android.podcasts3.ui.common.screenbehaviors.navigationbar.LocalNavigationBarBehaviorController
import com.greencom.android.podcasts3.ui.common.screenbehaviors.navigationbar.NavigationBarBehavior
import com.greencom.android.podcasts3.ui.common.screenbehaviors.navigationbar.NavigationBarBehaviorController
import com.greencom.android.podcasts3.utils.screenbehavior.DefaultScreenBehaviorController

@Composable
fun App() {
    // TODO: Do not forget to change
    val navigationBarBehaviorController =
        rememberNavigationBarBehaviorController()

    CompositionLocalProvider(
        LocalNavigationBarBehaviorController provides navigationBarBehaviorController,
    ) {
        Box {
            val navController = rememberNavController()
            AppNavigation(navController = navController)

            AppNavigationBar(
                navController = navController,
                modifier = Modifier.align(Alignment.BottomCenter),
            )

            // TODO: Add player
        }
    }
}

@Composable
private fun rememberNavigationBarBehaviorController(): NavigationBarBehaviorController {
    return remember {
        DefaultScreenBehaviorController(
            defaultBehavior = NavigationBarBehavior.Visible(isAnimated = false)
        )
    }
}
