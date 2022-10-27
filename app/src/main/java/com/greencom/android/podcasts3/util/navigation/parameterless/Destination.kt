package com.greencom.android.podcasts3.util.navigation.parameterless

import com.greencom.android.podcasts3.util.navigation.Destination

abstract class Destination : Destination<Unit>() {

    abstract override val routeSchema: String

    val route: String get() = routeSchema

    @Deprecated(
        message = "Use 'route' property instead",
        replaceWith = ReplaceWith("route"),
        level = DeprecationLevel.WARNING,
    )
    override fun createRoute(args: Unit): String = route

}