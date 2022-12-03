package com.greencom.android.podcasts3.ui.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.navigation.compose.rememberNavController
import com.greencom.android.podcasts3.ui.app.components.AppNavigationBar
import com.greencom.android.podcasts3.ui.common.compositionlocalproviders.LocalNavigationBarSizeTracker
import com.greencom.android.podcasts3.ui.player.PlayerBottomSheet
import com.greencom.android.podcasts3.ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        Box {
            // TODO: Replace with app navigation
            Surface(modifier = Modifier.fillMaxSize()) {}

            PlayerBottomSheet()

            val navigationBarSizeTracker = LocalNavigationBarSizeTracker.current
            AppNavigationBar(
                navController = rememberNavController(),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .onSizeChanged { navigationBarSizeTracker.onSizeChanged(it) },
            )
        }
    }
}
