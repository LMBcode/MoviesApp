package com.example.moviesapp.Fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.Adapter.MovieAdapters.RetrofitAdapterMovie
import com.example.moviesapp.Adapter.TVAdapters.RetrofitAdapterTrendingTV
import com.example.moviesapp.R
import com.example.moviesapp.Retrofit.Movie
import com.example.moviesapp.Retrofit.RetrofitInstance
import com.example.moviesapp.Slider.SliderAdapter
import com.example.moviesapp.databinding.FragmentHomeBinding
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations


class HomeFragment : Fragment() {
    private lateinit var _binding : FragmentHomeBinding
    private val binding get() = _binding
    private lateinit var layoutManager : LinearLayoutManager
    private lateinit var adapter : RetrofitAdapterTrendingTV
    private lateinit var adapterMovie : RetrofitAdapterMovie
    private lateinit var layoutManager2 : LinearLayoutManager
    private lateinit var sliderAdapter1 : SliderAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sliderAdapter1 = SliderAdapter{movie -> getArgs(movie)}
        adapter = RetrofitAdapterTrendingTV(mutableListOf()){ movie -> getArgs(movie)}
        adapterMovie =  RetrofitAdapterMovie(listOf()){movie -> getArgs(movie)}
        layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
        layoutManager2 = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
        binding.apply {
            recyclerRecommended.adapter = adapter
            recyclerRecommended.layoutManager = layoutManager
            recyclerMovie.adapter = adapterMovie
            recyclerMovie.layoutManager =  layoutManager2

            imageSlider.apply{
                setSliderAdapter(sliderAdapter1)
                setIndicatorAnimation(IndicatorAnimationType.WORM)
                setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
                setIndicatorUnselectedColor(Color.GRAY)
                startAutoCycle()
                setScrollTimeInSec(3)
            }
        }
        RetrofitInstance.TrendingTV(OnSuccess = ::setData, OnError = ::onError)
        RetrofitInstance.TrendingMovie(onSuccess = ::setMovie, onError = ::onError)
        RetrofitInstance.airing_today(onSuccess = ::SliderData, onError = ::onError)
    }
    private fun SliderData(movie : List<Movie>){
        sliderAdapter1.renewItems(movie)
    }

    private fun setData(movie : List<Movie>){
        adapter.setData(movie)
    }
    private fun setMovie(movie: List<Movie>){
        adapterMovie.setData(movie)

    }
    private fun onError(){
        Log.d("Response","ERROR")
    }

    private fun getArgs(movie : Movie){
       val args = Bundle()
        args.putSerializable(MOVIE_BACKDROP,movie.backdropPath)
        args.putSerializable(MOVIE_TITLE,movie.title)
        args.putSerializable(MOVIE_NAME,movie.name)
        args.putSerializable(MOVIE_OVERVIEW,movie.overview)
        args.putSerializable(MOVIE_RATING, movie.rating)
        args.putSerializable(MOVIE_POSTER,movie.posterPath)
        args.putSerializable(MOVIE_RELEASE_DATE,movie.releaseDate)
        args.putSerializable(TV_DATE,movie.airDate)
        findNavController().navigate(R.id.movieDetailsFragment,args)
    }


}

