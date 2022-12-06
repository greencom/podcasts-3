package com.greencom.android.podcasts3.utils

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.greencom.android.podcasts3.ui.navigation.AppNavigationItem

fun NavController.navigateToNavigationItem(item: AppNavigationItem) {
    navigate(item.route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
