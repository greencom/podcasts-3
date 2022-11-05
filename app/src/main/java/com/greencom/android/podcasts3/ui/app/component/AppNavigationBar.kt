package com.greencom.android.podcasts3.ui.app.component

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.greencom.android.podcasts3.ui.navigation.AppNavigationItem
import com.greencom.android.podcasts3.ui.navigation.AppNavigationItems
import com.greencom.android.podcasts3.util.navigation.navigateToNavigationItem

@Composable
fun AppNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        AppNavigationItems.forEach { item ->
            val isSelected = currentDestination?.hierarchy
                ?.any { it.route == item.route } == true

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigateToNavigationItem(item)
                },
                icon = { NavigationBarItemIcon(item, isSelected) },
                label = {
                    Text(
                        text = stringResource(item.labelResId),
                        style = MaterialTheme.typography.labelLarge,
                    )
                },
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun NavigationBarItemIcon(
    item: AppNavigationItem,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    AnimatedContent(
        modifier = modifier,
        targetState = isSelected,
        transitionSpec = {
            fadeIn(NavigationBarItemAnimationSpec) with fadeOut(NavigationBarItemAnimationSpec)
        },
    ) { mIsSelected ->
        val iconResId = if (mIsSelected) item.iconSelectedResId else item.iconUnselectedResId
        Icon(
            painter = painterResource(iconResId),
            contentDescription = stringResource(item.labelResId),
        )
    }
}

private val NavigationBarItemAnimationSpec = tween<Float>(durationMillis = 140)
