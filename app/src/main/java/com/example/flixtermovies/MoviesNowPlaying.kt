package com.example.flixtermovies

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MoviesNowPlaying(
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("poster_path")
    var movieCoverUrl: String? = null,
    @SerializedName("overview")
    var description: String? = null) : java.io.Serializable


