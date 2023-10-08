package com.example.movie.data

import com.google.gson.annotations.SerializedName

data class MovieListData(
    @SerializedName("Search")
    var search: List<Search> = emptyList(),
    @SerializedName("totalResults")
    var totalResults: String = "",
    @SerializedName("Response")
    var response: String = ""
)
