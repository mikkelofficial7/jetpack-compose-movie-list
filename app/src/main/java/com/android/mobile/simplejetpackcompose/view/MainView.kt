package com.android.mobile.simplejetpackcompose.view

import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.mobile.simplejetpackcompose.model.Movie
import com.android.mobile.simplejetpackcompose.nav.Destinations.Detail
import com.android.mobile.simplejetpackcompose.ui.theme.Purple500
import com.android.mobile.simplejetpackcompose.ui.theme.Purple700
import com.android.mobile.simplejetpackcompose.ui.theme.Teal200
import com.android.mobile.simplejetpackcompose.viewmodel.MovieComposeModel

@Composable
fun mainView(navController: NavController, actionBack: () -> Unit) {
    if (navController.currentBackStackEntry?.savedStateHandle?.contains("lastMovieClick") == true) {
        val result = navController.currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Movie>("lastMovieClick")?.value

        Column {
            topNavigationBar(titleText = "Home", action = actionBack)
            MainBody(navController, result)
        }

    } else {
        Column {
            topNavigationBar(titleText = "Home", action = actionBack)
            MainBody(navController)
        }

    }
}

@Composable
fun MainBody(navController: NavController, movieLastClick: Movie? = null) {
    val composeModel = MovieComposeModel()
    composeModel.populateData()

    LazyColumn(content = {
        itemsIndexed(composeModel.getMovieList()) { index, item ->
            mainLayer(navController, movie = composeModel.getMovieList()[index], movieLastClick = movieLastClick)
        }
    })

}

@Composable
fun mainLayer(navController: NavController, movie: Movie, movieLastClick: Movie?) {

    val modifierBox: Modifier = when(movie.title == movieLastClick?.title) {
        true -> {
            Modifier
                .padding(10.dp)
                .background(color = Teal200)
                .fillMaxWidth()
                .clickable {
                    navigateToMovieDetail(navController, movie)
                }
        }
        else -> {
            Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clickable {
                    navigateToMovieDetail(navController, movie)
                }
        }
    }

    Box(modifier = modifierBox) {
        Row {
            loadImage(movie = movie)
            Column {
                loadText(movie.title, Purple500)
                loadText("Release on: ${movie.releaseYear}", Purple700)

                Row {
                    loadText("Genre :", Purple500)
                    Column {
                        for (genre in movie.genres) {
                            loadText(word = "# $genre", color = Purple500)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun loadText(word: String, color: Color) {
    Text(
        text = word,
        style = TextStyle(color = color),
        modifier = Modifier.padding(5.dp)
    )
}

@Composable
fun loadImage(movie: Movie) {
    Image(
        painter = painterResource(movie.imageResource ?: 0),
        contentScale = ContentScale.Fit,
        modifier = Modifier.width(144.dp),
        contentDescription = movie.title
    )
}

private fun navigateToMovieDetail(navController: NavController, movie: Movie) {
    navController.navigate("$Detail/${movie.toJson}")
}