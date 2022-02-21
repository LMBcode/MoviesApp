package com.example.moviesapp.utils

import com.example.moviesapp.Retrofit.Api

class ApiHelper(private val api : Api) {

    suspend fun getUpcoming() = api.upcoming(page = 1)
}