package com.example.movie.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import coil.load
import com.example.movie.data.MovieData
import com.example.movie.databinding.FragmentMovieDetailsBinding


/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieData = arguments?.getParcelable("movieData", MovieData::class.java)
        movieData?.let {
            setupUI(it)
        }
    }

    private fun setupUI(movieData: MovieData) {
        bindImage(binding.ivPoster, movieData.poster)
        binding.apply {
            tvMovieName.text = movieData.title
            tvGenre.text = movieData.genre
            tvRelease.text = movieData.released
            tvRunTime.text = movieData.runtime
        }
    }

    private fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            imgView.load(imgUri)
        }
    }

}