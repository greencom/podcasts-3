package com.greencom.android.podcasts3.ui.app.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.greencom.android.podcasts3.ui.common.compositionlocalproviders.LocalNavigationBarSizeTracker
import com.greencom.android.podcasts3.ui.common.screenbehaviors.navigationbar.LocalNavigationBarBehaviorController
import com.greencom.android.podcasts3.ui.common.screenbehaviors.navigationbar.NavigationBarBehavior
import com.greencom.android.podcasts3.ui.navigation.AppNavigationItem
import com.greencom.android.podcasts3.ui.navigation.AppNavigationItems

private const val HeightThresholdPx = 1

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navigationBarBehaviorController =
        LocalNavigationBarBehaviorController.current
    val navigationBarBehavior by navigationBarBehaviorController.currentBehavior
    val isVisible = navigationBarBehavior is NavigationBarBehavior.Visible
    val isAnimated = navigationBarBehavior.isAnimated

    val navigationBarSizeTracker = LocalNavigationBarSizeTracker.current

    AnimatedVisibility(
        visible = isVisible,
        enter = if (isAnimated) expandVertically() else EnterTransition.None,
        exit = if (isAnimated) shrinkVertically() else ExitTransition.None,
        modifier = modifier.onSizeChanged {
            val size = when {
                // Workaround for cases when the animation is disabled
                !isVisible && !isAnimated -> IntSize.Zero
                // Height remains 1px after the bar disappears 🥴. TODO: Fix
                it.height <= HeightThresholdPx -> IntSize(it.width, 0)
                else -> it
            }
            navigationBarSizeTracker.onSizeChanged(size)
        },
    ) {
        AppNavigationBarContent(
            navController = navController,
            modifier = Modifier.animateEnterExit(
                enter = if (isAnimated) slideInVertically { it } else EnterTransition.None,
                exit = if (isAnimated) slideOutVertically { it } else ExitTransition.None,
            ),
        )
    }
}

@Composable
private fun AppNavigationBarContent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
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
                icon = {
                    NavigationBarItemIcon(item, isSelected)
                },
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

private val NavigationBarItemAnimationSpec = tween<Float>(durationMillis = 140)

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

private fun NavController.navigateToNavigationItem(item: AppNavigationItem) {
    navigate(item.route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
