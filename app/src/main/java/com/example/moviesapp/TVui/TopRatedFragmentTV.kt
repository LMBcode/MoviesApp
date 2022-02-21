package com.example.moviesapp.TVui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.Adapter.TVAdapters.RetrofitAdapterTV
import com.example.moviesapp.Fragments.*
import com.example.moviesapp.R
import com.example.moviesapp.Retrofit.Movie
import com.example.moviesapp.Retrofit.RetrofitInstance
import com.example.moviesapp.databinding.FragmentPopulartvBinding
import com.example.moviesapp.databinding.FragmentTopratedtvBinding


class TopRatedFragmentTV : Fragment() {
    private lateinit var _binding : FragmentTopratedtvBinding
    private val binding get() = _binding
    private lateinit var layoutManager : LinearLayoutManager
    private lateinit var adapter : RetrofitAdapterTV
    private var popularMoviesPage = 1
    var loading = false
    var lastPage = false
    var pageSize = 5
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTopratedtvBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = RetrofitAdapterTV(mutableListOf()){ movie -> getArgs(movie)}
        layoutManager = LinearLayoutManager(this.context)
        binding.recyclerViewTVP.adapter = adapter
        binding.recyclerViewTVP.layoutManager = layoutManager
        getPopularTV()
    }

    fun getPopularTV(){
        RetrofitInstance.tvTopRated(popularMoviesPage, onSuccess = ::Movies , onError = ::onError)
    }
    private fun Movies(movie: List<Movie>){
        adapter.setData(movie)
        pagination()
    }
    private fun onError(){
        Log.d("TAG","ERROR")
    }

    private fun  pagination(){
        binding.recyclerViewTVP.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if(!loading && !lastPage) {
                    if ((firstVisibleItem + visibleItemCount) >= totalItemCount &&
                        firstVisibleItem >= 0
                        && totalItemCount >= pageSize){
                        popularMoviesPage ++
                        loading = true
                        getPopularTV()
                        Log.d("TAG1",popularMoviesPage.toString())
                    }

                }
            }
        })
    }
    private fun getArgs(movie : Movie){
        val args = Bundle()
        args.putSerializable(MOVIE_BACKDROP,movie.backdropPath)
        args.putSerializable(MOVIE_TITLE,movie.title)
        args.putSerializable(MOVIE_OVERVIEW,movie.overview)
        args.putSerializable(MOVIE_RATING, movie.rating.toString())
        args.putSerializable(MOVIE_POSTER,movie.posterPath)
        args.putSerializable(MOVIE_RELEASE_DATE,movie.releaseDate)
        findNavController().navigate(R.id.movieDetailsFragment,args)
    }
    }
