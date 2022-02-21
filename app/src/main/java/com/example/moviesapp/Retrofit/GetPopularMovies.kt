package com.example.moviesapp.Retrofit

import com.google.gson.annotations.SerializedName

data class GetMovies(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val pages: Int,
    @SerializedName("release_date") val release_date : String,
    @SerializedName("poster_path") val image: String,
)
