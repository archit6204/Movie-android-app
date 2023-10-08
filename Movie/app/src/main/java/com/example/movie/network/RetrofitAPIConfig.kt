package com.example.movie.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAPIConfig {

    companion object {
        fun getApiService(): RetrofitAPIService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RetrofitAPIService::class.java)
        }
    }
}