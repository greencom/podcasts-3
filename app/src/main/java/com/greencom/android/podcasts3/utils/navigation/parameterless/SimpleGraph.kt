package com.greencom.android.podcasts3.utils.navigation.parameterless

import com.greencom.android.podcasts3.utils.navigation.Graph

abstract class SimpleGraph : Graph<Unit>() {

    abstract override val routeSchema: String

    val route: String get() = routeSchema

    @Deprecated(
        message = "Use 'route' property instead",
        replaceWith = ReplaceWith("route"),
        level = DeprecationLevel.WARNING,
    )
    override fun createRoute(args: Unit): String = route

}
