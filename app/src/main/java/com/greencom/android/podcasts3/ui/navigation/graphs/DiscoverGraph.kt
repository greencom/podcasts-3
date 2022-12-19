package com.greencom.android.podcasts3.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.greencom.android.podcasts3.ui.common.tooling.Placeholder
import com.greencom.android.podcasts3.utils.navigation.composableDestination
import com.greencom.android.podcasts3.utils.navigation.navigationGraph
import com.greencom.android.podcasts3.utils.navigation.parameterless.SimpleDestination
import com.greencom.android.podcasts3.utils.navigation.parameterless.SimpleGraph

object DiscoverGraph : SimpleGraph() {

    override val routeSchema = "discoverGraph"
    override val startDestination = Discover

    object Discover : SimpleDestination() {
        override val routeSchema = "discover"
    }

}

fun NavGraphBuilder.discoverGraph(navController: NavHostController) {
    navigationGraph(DiscoverGraph) {
        composableDestination(DiscoverGraph.Discover) {
            Placeholder("Discover")
        }
    }
}
