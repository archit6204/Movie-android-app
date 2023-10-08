package com.example.movie.data

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("Title")
    var title: String = "",
    @SerializedName("Year")
    var year: String = "",
    @SerializedName("imdbID")
    var imdbID: String = "",
    @SerializedName("Type")
    var type: String = "",
    @SerializedName("Poster")
    var poster: String = ""

)