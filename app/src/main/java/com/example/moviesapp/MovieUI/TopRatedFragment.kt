package com.example.moviesapp.MovieUI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.Adapter.MovieAdapters.RetrofitAdapter
import com.example.moviesapp.Fragments.*
import com.example.moviesapp.R
import com.example.moviesapp.Retrofit.Movie
import com.example.moviesapp.Retrofit.RetrofitInstance
import com.example.moviesapp.databinding.FragmentTopratedBinding


class TopRatedFragment : Fragment() {
    private lateinit var _binding : FragmentTopratedBinding
    private val binding get() =  _binding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter : RetrofitAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTopratedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         adapter = RetrofitAdapter(mutableListOf()){movie -> getArgs(movie)}
        layoutManager = LinearLayoutManager(this.context)
        binding.recyclerViewTopRated.adapter = adapter
        binding.recyclerViewTopRated.layoutManager = layoutManager

        RetrofitInstance.getTopRatedMovies(onSuccess = ::Movies , onError = ::onError)
    }
    private fun Movies(movie: List<Movie>){
        adapter.setData(movie)
    }
    private fun onError(){
        Log.d("TAG","ERROR")
    }

    private fun getArgs(movie : Movie){
        val args = Bundle()
        args.putSerializable(MOVIE_BACKDROP,movie.backdropPath)
        args.putSerializable(MOVIE_TITLE,movie.title)
        args.putSerializable(MOVIE_OVERVIEW,movie.overview)
        args.putSerializable(MOVIE_RATING, movie.rating)
        args.putSerializable(MOVIE_POSTER,movie.posterPath)
        args.putSerializable(MOVIE_RELEASE_DATE,movie.releaseDate)
        findNavController().navigate(R.id.movieDetailsFragment,args)
    }

}