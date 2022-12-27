package com.greencom.android.podcasts3.utils.navigation

import androidx.navigation.NamedNavArgument

abstract class SimpleDestination : Destination<Unit>() {

    abstract override val routeSchema: String

    val route: String get() = routeSchema

    @Deprecated(
        message = "Use 'route' property instead",
        replaceWith = ReplaceWith("route"),
        level = DeprecationLevel.WARNING,
    )
    override fun createRoute(args: Unit): String = route

    final override val arguments: List<NamedNavArgument>
        get() = super.arguments

}
