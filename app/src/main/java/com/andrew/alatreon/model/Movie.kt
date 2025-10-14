package com.andrew.alatreon.model

import com.google.gson.annotations.SerializedName

/** This is the data class in charge of storing the main information of each movie. */
data class Movie(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("poster_path")
    val imgUrl: String? = "",
) {
    override fun toString() = "id: $id, title: $title, posterImgUrl: $imgUrl"
}
