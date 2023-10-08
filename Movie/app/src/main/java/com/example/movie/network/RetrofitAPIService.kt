package com.example.movie.network

import com.example.movie.data.MovieData
import com.example.movie.data.MovieListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPIService {

    @GET("?apiKey=c8d30391")
    fun getMovies(
        @Query("s") search: String
    ): Call<MovieListData>

    @GET("?apiKey=c8d30391")
    fun getMovieData(
        @Query("i") imdbId: String
    ): Call<MovieData>
}