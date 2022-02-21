package com.example.moviesapp.utils

import com.example.moviesapp.Retrofit.Api

class MovieRepository (private val api : Api) {

    suspend fun getTvTopRated() = api.getTvTopRated(page = 1)
}