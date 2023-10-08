package com.example.movie.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class MovieData(
    @SerializedName("Title")
    var title: String? = "",
    @SerializedName("Year")
    var year: String? = "",
    @SerializedName("Rated")
    var rated: String? = "",
    @SerializedName("Released")
    var released: String? = "",
    @SerializedName("Genre")
    var genre: String? = "",
    @SerializedName("Poster")
    var poster: String? = "",
    @SerializedName("Runtime")
    var runtime: String? = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(year)
        parcel.writeString(rated)
        parcel.writeString(released)
        parcel.writeString(genre)
        parcel.writeString(poster)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieData> {
        override fun createFromParcel(parcel: Parcel): MovieData {
            return MovieData(parcel)
        }

        override fun newArray(size: Int): Array<MovieData?> {
            return arrayOfNulls(size)
        }
    }
}