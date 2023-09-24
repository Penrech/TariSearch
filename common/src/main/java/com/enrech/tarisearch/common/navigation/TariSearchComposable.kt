package com.enrech.tarisearch.common.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink

fun NavGraphBuilder.tariSearchComposable(
    destination: Destination,
    enterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = { slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Left) },
    exitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = { slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Left) },
    popEnterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = { slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Right) },
    popExitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = { slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Right) },
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    val route = destination.routeDestination
    val arguments = destination.getNavArguments(withExtras = true)

    val deepLinks = listOf(navDeepLink { uriPattern = destination.deepLinkDestination })

    return composable(route, arguments, deepLinks, enterTransition, exitTransition, popEnterTransition, popExitTransition, content)
}
