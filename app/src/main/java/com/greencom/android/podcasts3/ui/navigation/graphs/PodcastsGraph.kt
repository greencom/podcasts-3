package com.greencom.android.podcasts3.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.greencom.android.podcasts3.ui.common.tooling.Placeholder
import com.greencom.android.podcasts3.utils.navigation.composableDestination
import com.greencom.android.podcasts3.utils.navigation.navigationGraph
import com.greencom.android.podcasts3.utils.navigation.parameterless.Graph
import com.greencom.android.podcasts3.utils.navigation.parameterless.SimpleDestination

object PodcastsGraph : Graph() {

    override val routeSchema = "podcastsGraph"
    override val startDestination = Podcasts

    object Podcasts : SimpleDestination() {
        override val routeSchema = "podcasts"
    }

}

fun NavGraphBuilder.podcastsGraph(navController: NavHostController) {
    navigationGraph(PodcastsGraph) {
        composableDestination(PodcastsGraph.Podcasts) {
            Placeholder("Podcasts")
        }
    }
}
