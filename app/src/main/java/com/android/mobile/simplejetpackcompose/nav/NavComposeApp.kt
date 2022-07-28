package com.android.mobile.simplejetpackcompose.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.mobile.simplejetpackcompose.model.Movie
import com.android.mobile.simplejetpackcompose.model.MovieNavConverter
import com.android.mobile.simplejetpackcompose.nav.Destinations.Detail
import com.android.mobile.simplejetpackcompose.nav.Destinations.Main
import com.android.mobile.simplejetpackcompose.ui.theme.SimpleJetpackComposeTheme
import com.android.mobile.simplejetpackcompose.view.detailView
import com.android.mobile.simplejetpackcompose.view.mainView

@Composable
fun navComposeApp() {
    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }

    SimpleJetpackComposeTheme {
        NavHost(navController = navController, startDestination = Main) {
            composable(route = Main) {
                mainView(navController, actionBack = actions.navigateBack)
            }
            composable(
                route = "$Detail/{movieData}",
                arguments = listOf(navArgument("movieData") {
                    type = MovieNavConverter()
                })
            ) {
                val movie = it.arguments?.getParcelable<Movie>("movieData")

                detailView(navController,
                    movieData = movie,
                    actionBack = actions.navigateBackWithState("lastMovieClick", movie)
                )
            }
        }
    }
}