package com.example.moviesapp.Adapter.MovieAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.example.moviesapp.R
import com.example.moviesapp.Retrofit.Movie

class RetrofitAdapterMovie(private var movies : List<Movie>,private val onMovieClick : (movie : Movie) -> Unit) : RecyclerView.Adapter<RetrofitAdapterMovie.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.image)
        val name : TextView = itemView.findViewById(R.id.name)
        val rating: TextView = itemView.findViewById(R.id.rating)

        fun Bind(movie: Movie){
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(FitCenter())
                .into(image)
        }
    }
    fun setData(movies : List<Movie>){
        this.movies = movies
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.homelayout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = movies[position]
        fun bind(movie: Movie){
            holder.image.setOnClickListener { onMovieClick.invoke(movie) }
        }
        holder.apply {
            Bind(current)
            name.text = current.title
            rating.text = current.rating.toString()
            bind(current)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}