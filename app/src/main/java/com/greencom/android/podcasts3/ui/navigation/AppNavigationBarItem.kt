package com.greencom.android.podcasts3.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.greencom.android.podcasts3.R
import com.greencom.android.podcasts3.ui.common.AppIcons
import com.greencom.android.podcasts3.ui.navigation.destination.AppNavigationBarDestination

sealed class AppNavigationBarItem(
    val routeSchema: String,
    @StringRes val labelResId: Int,
    @DrawableRes val iconSelectedResId: Int,
    @DrawableRes val iconUnselectedResId: Int,
) {

    object Podcasts : AppNavigationBarItem(
        routeSchema = AppNavigationBarDestination.Podcasts.routeSchema,
        labelResId = R.string.navigation_bar_podcasts,
        iconSelectedResId = AppIcons.NavigationBarPodcastsFilled,
        iconUnselectedResId = AppIcons.NavigationBarPodcastsOutlined,
    )

    object Discover : AppNavigationBarItem(
        routeSchema = AppNavigationBarDestination.Discover.routeSchema,
        labelResId = R.string.navigation_bar_discover,
        iconSelectedResId = AppIcons.NavigationBarDiscoverFilled,
        iconUnselectedResId = AppIcons.NavigationBarDiscoverOutlined,
    )

}
