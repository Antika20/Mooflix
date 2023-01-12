package com.gdscug.mooflix.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesEntity(
    // Gnerate data
    val id:String?,
    val title: String?,
    val overview: String?,
    val releaaseDate: String,
    val voteAverage: String?,
    val posterPath: String,
):Parcelable

