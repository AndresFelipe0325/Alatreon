package com.andrew.alatreon.model

/** This is the data class in charge of storing the main information of each movie. */
data class Movie(
    val id: Int? = 0,
    val title: String? = "",
    val imgUrl: String? = "",
) {
    override fun toString() = "id: $id, title: $title, posterImgUrl: $imgUrl"
}
