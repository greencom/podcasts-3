package com.greencom.android.podcasts3.utils.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation

fun NavGraphBuilder.composableDestination(
    destination: Destination<*>,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = destination.routeSchema,
        arguments = destination.arguments,
        deepLinks = destination.deepLinks,
        content = content,
    )
}

fun NavGraphBuilder.dialogDestination(
    destination: Destination<*>,
    dialogProperties: DialogProperties = DialogProperties(),
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    dialog(
        route = destination.routeSchema,
        arguments = destination.arguments,
        deepLinks = destination.deepLinks,
        dialogProperties = dialogProperties,
        content = content,
    )
}

inline fun NavGraphBuilder.navigationGraph(
    graph: Graph<*>,
    builder: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = graph.routeSchema,
        startDestination = graph.startDestination.routeSchema,
        builder = builder,
    )
}
