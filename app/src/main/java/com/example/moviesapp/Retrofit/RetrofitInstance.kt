package com.example.moviesapp.Retrofit

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val api : Api

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }
    fun getTopRatedMovies(page : Int = 1,
    onSuccess: (movies: List<Movie>) -> Unit,
    onError: () -> Unit){
        api.getTopRated(page = page)
            .enqueue(object : Callback<GetMovies>{
                override fun onResponse(call: Call<GetMovies>, response: Response<GetMovies>) {
                    if (response.isSuccessful){
                        val responeBody = response.body()
                        if(responeBody != null){
                            onSuccess.invoke(responeBody.movies)
                        }
                        else{onError.invoke()}
                    }
                    else{onError.invoke()}
                }

                override fun onFailure(call: Call<GetMovies>, t: Throwable) {
                    Log.e("Repository","Failed")
                }

            })
    }
    fun getPopularMovies(page: Int = 1,
                         onSuccess: (movies: List<Movie>) -> Unit,
                         onError: () -> Unit) {
        api.getPopularMovies(page = page)
            .enqueue(object : Callback<GetMovies> {
                override fun onResponse(
                    call: Call<GetMovies>,
                    response: Response<GetMovies>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    }
                    else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetMovies>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                }
            })
    }
    fun TrendingTV(page : Int = 1,
    OnSuccess : (movies : List<Movie>) -> Unit,
    OnError : () -> Unit){
        api.TrendingTV(page = page,
        )
            .enqueue(object : Callback<GetMovies>{
                override fun onResponse(call: Call<GetMovies>, response: Response<GetMovies>) {
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null){
                            OnSuccess.invoke(responseBody.movies)
                        }
                        else{OnError.invoke()}
                    }
                    else{OnError.invoke()}
                }

                override fun onFailure(call: Call<GetMovies>, t: Throwable) {
                    Log.e("Repository","Error",t)
                }

            })
    }
    fun TrendingMovie(page : Int = 1,
                      onSuccess: (movies: List<Movie>) -> Unit,
                      onError: () -> Unit){
        api.TrendingMovie(page = page)
            .enqueue(object : Callback<GetMovies>{
                override fun onResponse(call: Call<GetMovies>, response: Response<GetMovies>) {
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if(responseBody != null){
                            onSuccess.invoke(responseBody.movies)
                        }
                        else{onError.invoke()}
                    }
                    else{onError.invoke()}
                }

                override fun onFailure(call: Call<GetMovies>, t: Throwable) {
                    Log.e("Repository","Error",t)
                }

            })
    }
    fun getTVpopular(page : Int = 1,
                     OnSuccess: (movies: List<Movie>) -> Unit,
                     OnError: () -> Unit){
        api.TVpopular(page = page)
            .enqueue(object : Callback<GetMovies>{
                override fun onResponse(call: Call<GetMovies>, response: Response<GetMovies>) {
                    if (response.isSuccessful){
                        val responeBody = response.body()
                        if (responeBody != null){
                            OnSuccess.invoke(responeBody.movies)
                        }
                        else{OnError.invoke()}
                    }
                    else{OnError.invoke()}
                }

                override fun onFailure(call: Call<GetMovies>, t: Throwable) {
                    Log.e("Repository","Error",t)
                }

            })
    }
    fun airing_today(page : Int = 1,
                     onSuccess: (movies: List<Movie>) -> Unit,
                     onError: () -> Unit){
        api.airing_today(page = page)
            .enqueue(object  : Callback<GetMovies>{
                override fun onResponse(call: Call<GetMovies>, response: Response<GetMovies>) {
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if(responseBody != null){
                            onSuccess.invoke(responseBody.movies)
                        }
                        else{onError.invoke()}
                    }
                    else{onError.invoke()}
                }

                override fun onFailure(call: Call<GetMovies>, t: Throwable) {
                    Log.e("Repository","Error",t)
                }

            })
    }

    fun tvTopRated(page : Int = 1,
                 onSuccess: (movies: List<Movie>) -> Unit,
                 onError: () -> Unit){
        api.getTvTopRated(page = page)
            .enqueue(object : Callback<GetMovies>{
                override fun onResponse(call: Call<GetMovies>, response: Response<GetMovies>) {
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null){
                            onSuccess.invoke(responseBody.movies)
                        }
                        else{onError.invoke()}
                    }
                    else{onError.invoke()}
                }

                override fun onFailure(call: Call<GetMovies>, t: Throwable) {
                    Log.e("Repository","Error",t)
                }

            })
    }
    fun getUpcoming(page : Int = 1,
                    onSuccess: (movies: List<Movie>) -> Unit,
                    onError: () -> Unit){
        api.upcoming(page = 1)
            .enqueue(object : Callback<GetMovies>{
                override fun onResponse(call: Call<GetMovies>, response: Response<GetMovies>) {
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null){
                            onSuccess.invoke(responseBody.movies)
                        }
                        else{onError.invoke()}
                    }
                    else{onError.invoke()}
                }

                override fun onFailure(call: Call<GetMovies>, t: Throwable) {
                    Log.e("Repository","Error",t)
                }

            })
    }

    fun getUpcomingTV(page : Int = 1,
                    onSuccess: (movies: List<Movie>) -> Unit,
                    onError: () -> Unit){
        api.upcomingtv(page = 1)
            .enqueue(object : Callback<GetMovies>{
                override fun onResponse(call: Call<GetMovies>, response: Response<GetMovies>) {
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null){
                            onSuccess.invoke(responseBody.movies)
                        }
                        else{onError.invoke()}
                    }
                    else{onError.invoke()}
                }

                override fun onFailure(call: Call<GetMovies>, t: Throwable) {
                    Log.e("Repository","Error",t)
                }

            })
    }
}