package com.example.moviesapp.MovieUI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.Adapter.MovieAdapters.RetrofitAdapter
import com.example.moviesapp.Fragments.*
import com.example.moviesapp.R
import com.example.moviesapp.Retrofit.Movie
import com.example.moviesapp.Retrofit.RetrofitInstance
import com.example.moviesapp.databinding.FragmentPopularBinding


class PopularFragment : Fragment() {
    private lateinit var _binding : FragmentPopularBinding
    private val binding get() = _binding
    private lateinit var layoutManager : LinearLayoutManager
    private lateinit var adapter : RetrofitAdapter
    private var popularMoviesPage = 1
    var loading = false
    var lastPage = false
    val pageSize = 3
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPopularBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = RetrofitAdapter(mutableListOf()){movie -> getArgs(movie)}
        layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
        getPopularMovies()
        binding.searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }

        })
    }
    private fun getPopularMovies(){
        RetrofitInstance.getPopularMovies(popularMoviesPage, onSuccess = ::Movies,onError = ::onError )
    }

    private fun setData(){
        adapter.setDataFilter()
    }
    private fun Movies(movie: List<Movie>){
        adapter.setData(movie)
        pagination()

    }
    fun loadItems(){
        popularMoviesPage ++
        loading = true
        getPopularMovies()
    }
    private fun onError(){
        Log.d("TAG","ERROR")
    }

    private fun  pagination(){
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if(!loading && !lastPage) {
                    if ((firstVisibleItem + visibleItemCount) >= totalItemCount &&
                            firstVisibleItem >= 0
                            && totalItemCount >= pageSize){
                                loadItems()
                    }

                }
            }
        })
    }

    private fun getArgs(movie : Movie){
        val args = Bundle()
        args.putSerializable(MOVIE_TITLE,movie.title)
        args.putSerializable(MOVIE_POSTER,movie.posterPath)
        args.putSerializable(MOVIE_OVERVIEW,movie.overview)
        args.putSerializable(MOVIE_RATING,movie.rating)
        args.putSerializable(MOVIE_BACKDROP,movie.backdropPath)
        args.putSerializable(MOVIE_RELEASE_DATE,movie.releaseDate)
        findNavController().navigate(R.id.movieDetailsFragment,args)
    }

}
