package com.gdscug.mooflix.data.remote.response

import com.gdscug.mooflix.data.local.MoviesEntity
import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class MoviesResponse(
    @field:SerializedName("results")
    val results : ArrayList<MoviesEntity>
)
