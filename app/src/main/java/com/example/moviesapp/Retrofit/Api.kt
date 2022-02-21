package com.example.moviesapp.Retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "83971b0730e06699a4ecd862d849f336",
        @Query("page") page: Int
    ) : Call<GetMovies>

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key") apiKey: String = "83971b0730e06699a4ecd862d849f336",
        @Query("page") page: Int
    ): Call<GetMovies>

    @GET("trending/tv/day")
    fun TrendingTV(
        @Query("api_key") apiKey:String = "83971b0730e06699a4ecd862d849f336",
        @Query("page") page: Int
    ):Call<GetMovies>

    @GET("trending/movie/day")
    fun TrendingMovie(
        @Query("api_key") apiKey:String = "83971b0730e06699a4ecd862d849f336",
        @Query("page") page: Int
    ):Call<GetMovies>

    @GET("tv/popular")
    fun TVpopular(
        @Query("api_key") apiKey:String = "83971b0730e06699a4ecd862d849f336",
        @Query("page") page: Int
    ): Call<GetMovies>

    @GET("tv/airing_today")
    fun airing_today(
        @Query("api_key") apiKey : String =  "83971b0730e06699a4ecd862d849f336",
        @Query("page") page : Int
    ): Call<GetMovies>

    @GET("discover/tv")
    fun discover(
        @Query("api_key") apiKey : String =  "83971b0730e06699a4ecd862d849f336",
        @Query("page") page : Int,
        @Query("sort_by") sortBy : String = "first_air_date.desc",
        @Query("vote_count.gte") year : String ="2022"
    ) : Call<GetMovies>

    @GET("movie/upcoming")
    fun upcoming(
        @Query("api_key")  apiKey : String = "83971b0730e06699a4ecd862d849f336",
        @Query("page") page : Int,
        @Query("sort_by") sortBy : String = "release_date.desc"
    ) : Call<GetMovies>

    @GET("person/popular")
    fun upcomingtv(
        @Query("api_key")  apiKey : String = "83971b0730e06699a4ecd862d849f336",
        @Query("page") page : Int,
        @Query("sort_by") sortBy : String = "release_date.desc"
    ) : Call<GetMovies>

    @GET("tv/top_rated")
     fun getTvTopRated(
        @Query("api_key")  apiKey : String = "83971b0730e06699a4ecd862d849f336",
        @Query("page") page : Int
    ): Call<GetMovies>
}