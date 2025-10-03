package com.andrew.alatreon.model

/** This data class is in charge of storing genre of each movie. */
data class Genre(
    val id: Int? = 0,
    val name: String? = ""
) {
    override fun toString() = "id: $id, name: $name"
}
