package com.example.movie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie.data.MovieData
import com.example.movie.data.MovieListData
import com.example.movie.network.RetrofitAPIConfig
import retrofit2.Call
import retrofit2.Response

class MovieViewModel: ViewModel() {
     val movieListData = MutableLiveData<MovieListData>()
     val movieData = MutableLiveData<MovieData>()
     val errorMessage = MutableLiveData<String>()

     fun getAllMovieData(searchKeyword: String) {
        val client = RetrofitAPIConfig.getApiService().getMovies(search = searchKeyword)
        client.enqueue(object : retrofit2.Callback<MovieListData> {
            override fun onResponse(call: Call<MovieListData>, response: Response<MovieListData>) {
                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null) {
                    return
                }
                movieListData.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieListData>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        }
        )
    }

     fun getMovieData(imdbId: String) {
        val client = RetrofitAPIConfig.getApiService().getMovieData(imdbId = imdbId)
        client.enqueue(object : retrofit2.Callback<MovieData> {
            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null) {
                    return
                }
                movieData.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        }
        )
    }
}