package com.greencom.android.podcasts3.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.greencom.android.podcasts3.R
import com.greencom.android.podcasts3.ui.common.AppIcons
import com.greencom.android.podcasts3.ui.navigation.destination.AppNavigationBarDestination

val AppNavigationItems = listOf(
    AppNavigationItem.Podcasts,
    AppNavigationItem.Discover,
)

sealed class AppNavigationItem(
    val route: String,
    @StringRes val labelResId: Int,
    @DrawableRes val iconSelectedResId: Int,
    @DrawableRes val iconUnselectedResId: Int,
) {

    object Podcasts : AppNavigationItem(
        route = AppNavigationBarDestination.Podcasts.route,
        labelResId = R.string.navigation_bar_podcasts,
        iconSelectedResId = AppIcons.NavigationBarPodcastsFilled,
        iconUnselectedResId = AppIcons.NavigationBarPodcastsOutlined,
    )

    object Discover : AppNavigationItem(
        route = AppNavigationBarDestination.Discover.route,
        labelResId = R.string.navigation_bar_discover,
        iconSelectedResId = AppIcons.NavigationBarDiscoverFilled,
        iconUnselectedResId = AppIcons.NavigationBarDiscoverOutlined,
    )

}
