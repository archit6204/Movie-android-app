package com.example.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movie.data.MovieListData
import com.example.movie.data.Search
import com.example.movie.databinding.ItemMovieBinding
import com.example.movie.ui.MovieItemClickListener

class MovieAdapter(
    private val movieItemClickListener: MovieItemClickListener
) : RecyclerView.Adapter<MovieAdapterViewHolder>() {

    private lateinit var moviebinding: ItemMovieBinding
    private var movieData = mutableListOf<Search>()

    fun setMovieData(movieData: MovieListData) {
        this.movieData = movieData.search.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapterViewHolder {
        val binding = ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
            parent,
        false
        )
        moviebinding = binding
        return MovieAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapterViewHolder, position: Int) {
        val movie = movieData[position]
        moviebinding.tvMovieName.text = movie.title
        bindImage(moviebinding.ivPoster, movie.poster)
        handleMovieItemClick(movie.imdbID)

    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    private fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            imgView.load(imgUri)
        }
    }

    private fun handleMovieItemClick(imdbId: String) {
        moviebinding.root.setOnClickListener {
            movieItemClickListener.onMovieItemClick(imdbId)
        }
    }
}
