package com.example.moviesapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMovieDetailsBinding

const val MOVIE_BACKDROP = "extra_movie_backdrop"
const val MOVIE_POSTER = "extra_movie_poster"
const val MOVIE_TITLE= "extra_movie_title"
const val MOVIE_RATING = "extra_movie_rating"
const val MOVIE_RELEASE_DATE = "extra_movie_release_date"
const val MOVIE_OVERVIEW = "extra_movie_overview"
const val MOVIE_NAME = "extra_movie_name"
const val TV_DATE = "extra_tv_date"

class MovieDetailsFragment : Fragment() {
    
    private lateinit var _binding : FragmentMovieDetailsBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        populateDetails()
    }

    private fun populateDetails(){
        arguments?.getString(MOVIE_BACKDROP)?.let{ backdropPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280$backdropPath")
                .centerCrop()
                .into(binding.movieBackdrop)
        }
        arguments?.getString(MOVIE_POSTER)?.let{posterPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342$posterPath")
                .centerCrop()
                .into(binding.moviePoster)
        }
        binding.apply{
            movieTitle.text = arguments?.getString(MOVIE_TITLE,"")
            movieName.text = arguments?.getString(MOVIE_NAME,"")
            movieRating.rating = arguments?.getFloat(MOVIE_RATING,0f)?.div(2)!!
            movieOverview.text = arguments?.getString(MOVIE_OVERVIEW,"")
            movieReleaseDate.text = arguments?.getString(MOVIE_RELEASE_DATE,"")
            tvReleaseDate.text = arguments?.getString(TV_DATE,"")
        }
    }

}