package com.android.mobile.simplejetpackcompose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.mobile.simplejetpackcompose.model.Movie
import com.android.mobile.simplejetpackcompose.ui.theme.Purple500
import com.android.mobile.simplejetpackcompose.ui.theme.Purple700

@Composable
fun detailView(navController: NavController, movieData: Movie?, actionBack: () -> Unit) {
    Column {
        topNavigationBar(
            titleText = movieData?.title.orEmpty(),
            showBack = true,
            action = actionBack
        )

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            loadImageDetail(movie = movieData)
            loadTextDetail(
                word = movieData?.title.orEmpty(),
                color = Purple700,
                fontSize = 25.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
            loadTextDetail(
                word = "Release on ${movieData?.releaseYear.orEmpty()}",
                color = Purple700,
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic
            )

            Row {
                loadTextDetail(
                    word = "- ${populateGenre(movieData)} -",
                    color = Purple500,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic
                )
            }

            loadTextDetail(
                word = movieData?.summary.orEmpty(),
                color = Purple700,
                fontSize = 18.sp,
                fontStyle = FontStyle.Normal
            )
        }
    }
}

@Composable
fun loadTextDetail(word: String,
                   color: Color,
                   fontSize: TextUnit,
                   fontStyle: FontStyle,
                   fontWeight: FontWeight? = null
) {
    Text(
        text = word,
        style = TextStyle(
            color = color,
            fontSize = fontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight
        ),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    )
}

@Composable
fun loadImageDetail(movie: Movie?) {
    Image(
        painter = painterResource(movie?.imageResource ?: 0),
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        contentDescription = movie?.title
    )
}

private fun populateGenre(movieData: Movie?): String {
    var genreRow = ""

    movieData?.genres?.forEachIndexed { index, s ->
        genreRow += s

        if(index < movieData.genres.size - 1) {
            genreRow += ", "
        }
    }

    return genreRow
}