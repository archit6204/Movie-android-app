package com.example.movie.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.R
import com.example.movie.data.MovieData
import com.example.movie.data.MovieListData
import com.example.movie.databinding.ActivityMainBinding
import com.example.movie.ui.adapter.MovieAdapter
import com.example.movie.ui.viewmodel.MovieViewModel
import com.example.movie.ui.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity(), MovieItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupViewModel()
        binding.btnSearch.setOnClickListener {
            getAllMovieData()
        }
    }

    private fun getAllMovieData() {
        val searchKeyword = binding.etSearch.text.toString()
        viewModel.getAllMovieData(searchKeyword)
   }

    private fun setupRecyclerView(responseBody: MovieListData) {
        movieAdapter = MovieAdapter(this)
        movieAdapter.setMovieData(responseBody)
        binding.rvMovies.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onMovieItemClick(imdbId: String) {
        viewModel.getMovieData(imdbId = imdbId)

    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory())[MovieViewModel::class.java]

        viewModel.movieListData.observe(this) {
            setupRecyclerView(it)
        }
        viewModel.errorMessage.observe(this) {
        }

        viewModel.movieData.observe(this) {
            startFragment(it)
        }
    }

    private fun startFragment(movieData: MovieData) {
        val bundle = Bundle()
        bundle.putParcelable("movieData", movieData)
        val movieDetailsFragment = MovieDetailsFragment()
        movieDetailsFragment.arguments = bundle
        this.supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, movieDetailsFragment)
            .addToBackStack(null)
            .commit()
        hideView()
    }

    private fun hideView() {
        binding.apply {
            rvMovies.visibility = View.GONE
            etSearch.visibility = View.GONE
            btnSearch.visibility = View.GONE
        }
    }

    private fun showView() {
        binding.apply {
            rvMovies.visibility = View.VISIBLE
            etSearch.visibility = View.VISIBLE
            btnSearch.visibility = View.VISIBLE
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
            showView()
        }
    }
}