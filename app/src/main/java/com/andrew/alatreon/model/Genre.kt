package com.andrew.alatreon.model

import com.google.gson.annotations.SerializedName

/** This data class is in charge of storing genre of each movie. */
data class Genre(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = ""
) {
    override fun toString() = "id: $id, name: $name"
}
