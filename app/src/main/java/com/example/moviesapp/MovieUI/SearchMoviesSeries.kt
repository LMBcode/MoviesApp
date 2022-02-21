package com.example.moviesapp.MovieUI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.Adapter.SearchAdapter
import com.example.moviesapp.Fragments.*
import com.example.moviesapp.R
import com.example.moviesapp.Retrofit.Movie
import com.example.moviesapp.Retrofit.RetrofitInstance
import com.example.moviesapp.databinding.SearchFragmentBinding


class SearchMoviesSeries : Fragment() {
    private lateinit var _binding : SearchFragmentBinding
    private val binding get() = _binding
    private lateinit var layoutManager : GridLayoutManager
    private lateinit var adapter : SearchAdapter
    private var popularMoviesPage = 1
    var loading = true
    var previousTotal = 0
    var lastPage = false
    val pageSize = 10
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = SearchFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = SearchAdapter(mutableListOf()){movie -> getArgs(movie)}
        layoutManager = GridLayoutManager(this.context,3)
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
                if(loading) {
                    if(totalItemCount > previousTotal ){
                        loading = false
                        previousTotal = totalItemCount
                        popularMoviesPage++
                        Log.d("TAG",popularMoviesPage.toString())
                        getPopularMovies()

                    }
                }
                if(!loading &&(totalItemCount - visibleItemCount) <= (firstVisibleItem + pageSize)){
                    loading = true
                }
            }
        })
    }
    fun onLoadMore(page : Int , totalItemsCount : Int){

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
