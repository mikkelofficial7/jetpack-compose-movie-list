package com.android.mobile.simplejetpackcompose.model

import android.net.Uri
import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val releaseYear: String,
    @DrawableRes val imageResource: Int,
    val summary: String,
    val genres: List<String>
) : Parcelable {
    val toJson : String
        get() {
            return Uri.encode(Gson().toJson(this))
        }
}
