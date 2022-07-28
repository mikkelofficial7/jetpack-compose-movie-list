package com.android.mobile.simplejetpackcompose.view

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.android.mobile.simplejetpackcompose.model.Movie
import com.android.mobile.simplejetpackcompose.nav.Destinations.Detail
import com.android.mobile.simplejetpackcompose.ui.theme.Purple500
import com.android.mobile.simplejetpackcompose.ui.theme.Purple700
import com.android.mobile.simplejetpackcompose.viewmodel.MovieComposeModel
import com.google.gson.Gson

@Composable
fun mainView(navController: NavController, actionBack: () -> Unit) {
    Column {
        topNavigationBar(titleText = "Home", action = actionBack)
        MainBody(navController)
    }
}

@Composable
fun MainBody(navController: NavController) {
    val composeModel = MovieComposeModel()
    composeModel.populateData()

    LazyColumn(content = {
        itemsIndexed(composeModel.getMovieList()) { index, item ->
            mainLayer(navController, movie = composeModel.getMovieList()[index])
        }
    })

}

@Composable
fun mainLayer(navController: NavController, movie: Movie) {
    Box(modifier = Modifier.padding(10.dp).fillMaxWidth().clickable {
        navigateToMovieDetail(navController, movie)
    }) {
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