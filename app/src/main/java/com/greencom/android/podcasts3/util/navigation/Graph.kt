package com.greencom.android.podcasts3.util.navigation

abstract class Graph<P> : Destination<P>() {

    abstract override val routeSchema: String

    abstract override fun createRoute(args: P): String

    abstract val startDestination: Destination<*>

}
