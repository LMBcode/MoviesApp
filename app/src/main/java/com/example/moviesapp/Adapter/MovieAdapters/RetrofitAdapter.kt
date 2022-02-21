package com.example.moviesapp.Adapter.MovieAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.example.moviesapp.R
import com.example.moviesapp.Retrofit.Movie

class RetrofitAdapter(private var movies : MutableList<Movie>, private val onMovieClick : (movie : Movie) -> Unit) : RecyclerView.Adapter<RetrofitAdapter.ViewHolder>(),Filterable {
    var moviesFilter = movies
    class ViewHolder(item : View) : RecyclerView.ViewHolder(item){
        val image : ImageView = itemView.findViewById(R.id.image)
        val title : TextView = itemView.findViewById(R.id.title)
        val rating: TextView = itemView.findViewById(R.id.rating)
        val release: TextView = itemView.findViewById(R.id.release)

        fun Bind(movie: Movie){
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(FitCenter())
                .into(image)
        }
    }
    fun setDataFilter(){
        this.moviesFilter = movies
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
            title.text = current.title
            rating.text = current.rating.toString()
            release.text = current.releaseDate
            bind(current)
        }
    }
     override fun getFilter() : Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if(constraint.isNullOrEmpty()){
                    filterResults.count = moviesFilter.size
                    filterResults.values = moviesFilter
                }
                else{
                    var searchLetter = constraint.toString().lowercase()
                    val itemEx = ArrayList<Movie>()
                    for(item in moviesFilter){
                        if(item.title.lowercase().contains(searchLetter)){
                            itemEx.add(item)
                        }
                    }
                    filterResults.count = itemEx.size
                    filterResults.values = itemEx
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                movies = results?.values as ArrayList<Movie>
                notifyDataSetChanged()
            }

        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}