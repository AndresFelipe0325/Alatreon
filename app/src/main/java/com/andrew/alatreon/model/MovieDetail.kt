package com.andrew.alatreon.model

import com.google.gson.annotations.SerializedName

/** This data class is in charge of storing details of each movie. */
data class MovieDetail(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("original_title")
    val title: String? = "",
    @SerializedName("overview")
    val overview: String? = "",
    @SerializedName("origin_country")
    val country: String? = "",
    @SerializedName("original_language")
    val language: String? = "",
    @SerializedName("release_date")
    val releaseDate: String? = "",
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("budget")
    val budget: Int? = 0,
    @SerializedName("homepage")
    val homepage: String? = "",
    @SerializedName("adult")
    val adult: Boolean? = false
) {
    override fun toString() = "id: $id, title: $title, overview: $overview, country: $country, " +
            "language: $language, releaseDate: $releaseDate, genre: $genres, status: $status, " +
            "budget: $budget, homepage: $homepage, adult: $adult"
}
