package com.example.moviesapp.Adapter.TVAdapters

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

class RetrofitAdapterTV(private var movies : MutableList<Movie>,private val onMovieClick : (movie : Movie) -> Unit) : RecyclerView.Adapter<RetrofitAdapterTV.ViewHolder>() {

    class ViewHolder(item : View) : RecyclerView.ViewHolder(item){
        val image : ImageView = itemView.findViewById(R.id.image)
        val name : TextView = itemView.findViewById(R.id.title)
        val rating: TextView = itemView.findViewById(R.id.rating)


        fun Bind(movie: Movie){
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(FitCenter())
                .into(image)
        }
    }
    fun setData(movies : List<Movie>){
        this.movies.addAll(movies)
        notifyItemRangeInserted(this.movies.size, movies.size-1
        )
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movies,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = movies[position]
        fun bind(movie : Movie){
            holder.image.setOnClickListener { onMovieClick.invoke(movie) }
        }
        holder.apply {
            Bind(current)
            name.text = current.name
            rating.text = current.rating.toString()
            bind(current)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}