package com.greencom.android.podcasts3.util.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

abstract class Destination<P> {

    abstract val routeSchema: String

    abstract fun createRoute(args: P): String

    open val arguments: List<NamedNavArgument>
        get() = emptyList()

    open val deepLinks: List<NavDeepLink>
        get() = emptyList()

}
