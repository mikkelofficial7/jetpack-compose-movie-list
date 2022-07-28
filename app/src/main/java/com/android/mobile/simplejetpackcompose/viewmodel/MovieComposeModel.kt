package com.android.mobile.simplejetpackcompose.viewmodel

import com.android.mobile.simplejetpackcompose.R
import com.android.mobile.simplejetpackcompose.model.Movie


class MovieComposeModel {
    private val movieList = ArrayList<Movie>()

    fun getMovieList() : ArrayList<Movie> {
        return movieList
    }

    fun populateData() {
        val genre1 = arrayListOf("Comedy", "Romance", "Action")
        movieList.add(Movie(
            imageResource = R.drawable.thor_love_and_thunder,
            title = "Thor: Love and Thunder",
            releaseYear = "2022",
            summary = "Thor enlists the help of Valkyrie, Korg and ex-girlfriend Jane Foster to fight Gorr the God Butcher, who intends to make the gods extinct.",
            genres = genre1
        ))

        val genre2 = arrayListOf("Comedy", "Sci-fi", "Action")
        movieList.add(Movie(
            imageResource = R.drawable.incredible_hulk,
            title = "The Incredible Hulk",
            releaseYear = "2008",
            summary = "Bruce Banner, a scientist on the run from the U.S. Government, must find a cure for the monster he turns into whenever he loses his temper.",
            genres = genre2
        ))

        val genre3 = arrayListOf("Romance", "Action")
        movieList.add(Movie(
            imageResource = R.drawable.gotg3,
            title = "Guardian of the Galaxy Vol. 3",
            releaseYear = "2023",
            summary = "Still reeling from the loss of Gamora, Peter Quill rallies his team to defend the universe and one of their own - a mission that could mean the end of the Guardians if not successful.",
            genres = genre3
        ))

        val genre4 = arrayListOf("Comedy", "Action")
        movieList.add(Movie(
            imageResource = R.drawable.black_widow,
            title = "Black Widow",
            releaseYear = "2021",
            summary = "Natasha Romanoff confronts the darker parts of her ledger when a dangerous conspiracy with ties to her past arises.",
            genres = genre4
        ))

        val genre5 = arrayListOf("Sci-fi", "Action")
        movieList.add(Movie(
            imageResource = R.drawable.captain_marvel,
            title = "Captain Marvel",
            releaseYear = "2019",
            summary = "Carol Danvers becomes one of the universe's most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.",
            genres = genre5
        ))

        val genre6 = arrayListOf("Sci-fi", "Action", "Comedy")
        movieList.add(Movie(
            imageResource = R.drawable.avenger_end_game,
            title = "Avengers: End Game",
            releaseYear = "2019",
            summary = "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
            genres = genre6
        ))
    }
}