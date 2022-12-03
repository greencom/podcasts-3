package com.greencom.android.podcasts3.ui.navigation.destination

import com.greencom.android.podcasts3.utils.navigation.parameterless.Destination

object AppNavigationBarDestination {

    object Podcasts : Destination() {
        override val routeSchema = "podcasts"
    }

    object Discover : Destination() {
        override val routeSchema = "discover"
    }

}
