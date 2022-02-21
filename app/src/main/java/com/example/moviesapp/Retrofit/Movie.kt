package com.example.moviesapp.Retrofit

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("name") val name : String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("profile_path") val profilePath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("overview") val overview : String,
    @SerializedName("first_air_date") val airDate : String
)
