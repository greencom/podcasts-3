package com.greencom.android.podcasts3.ui.app.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.greencom.android.podcasts3.ui.common.tooling.Placeholder
import com.greencom.android.podcasts3.ui.navigation.CommonDestinations
import com.greencom.android.podcasts3.ui.navigation.graphs.PodcastsGraph
import com.greencom.android.podcasts3.ui.navigation.graphs.discoverGraph
import com.greencom.android.podcasts3.ui.navigation.graphs.podcastsGraph
import com.greencom.android.podcasts3.utils.navigation.composableDestination

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = PodcastsGraph.route,
        modifier = modifier,
    ) {
        composableDestination(CommonDestinations.SignIn) {
            Placeholder("SignIn")
        }

        podcastsGraph(navController)
        discoverGraph(navController)
    }
}
