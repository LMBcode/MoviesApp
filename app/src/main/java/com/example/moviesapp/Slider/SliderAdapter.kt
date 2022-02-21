package com.example.moviesapp.Slider

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.example.moviesapp.R
import com.example.moviesapp.Retrofit.Movie
import com.example.moviesapp.Retrofit.RetrofitInstance
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(private val onMovieClick : (movie : Movie) -> Unit) : SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {

    var SliderItems : List<Movie> = ArrayList()

    fun renewItems(slideritems : List<Movie>){
        this.SliderItems = slideritems
        notifyDataSetChanged()
    }
    class SliderAdapterVH(itemView : View) : SliderViewAdapter.ViewHolder(itemView) {
            val image : ImageView = itemView.findViewById(R.id.iv_auto_image_slider)
            val imageGif : ImageView = itemView.findViewById(R.id.iv_gif_container)
            val textDesc : TextView = itemView.findViewById(R.id.tv_auto_image_slider)

        fun Bind(movie: Movie){
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(FitCenter())
                .into(image)
        }
    }
    override fun getCount(): Int {
        return SliderItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapter.SliderAdapterVH {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.imageslider,parent,false)
        return SliderAdapterVH(view)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapter.SliderAdapterVH, position: Int) {
        val current = SliderItems[position]
        fun bind(movie : Movie){
            viewHolder.image.setOnClickListener { onMovieClick.invoke(movie) }
        }
        bind(current)
        viewHolder.textDesc.text = current.name
        viewHolder.textDesc.textSize = 16F
        viewHolder.textDesc.setTextColor(Color.WHITE)
        viewHolder.Bind(current)
    }
}