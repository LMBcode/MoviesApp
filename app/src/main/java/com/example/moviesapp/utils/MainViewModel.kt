package com.example.moviesapp.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.Retrofit.GetMovies
import com.example.moviesapp.Retrofit.Movie
import kotlinx.coroutines.*
import okhttp3.Dispatcher

class MainViewModel(private val movieRepository : MovieRepository) : ViewModel(){
    val errorMessage = MutableLiveData<String>()
    val succesMessage = MutableLiveData<GetMovies>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }


    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }
}
