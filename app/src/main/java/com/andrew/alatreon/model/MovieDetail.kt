package com.andrew.alatreon.model

/** This data class is in charge of storing details of each movie. */
data class MovieDetail(
    val id: Int? = 0,
    val title: String? = "",
    val overview: String? = "",
    val country: String? = "",
    val language: String? = "",
    val releaseDate: String? = "",
    val genre: List<Genre>,
    val status: String? = "",
    val budget: Int? = 0,
    val homepage: String? = "",
    val adult: Boolean? = false
) {
    override fun toString() = "id: $id, title: $title, overview: $overview, country: $country, " +
            "language: $language, releaseDate: $releaseDate, genre: $genre, status: $status, " +
            "budget: $budget, homepage: $homepage, adult: $adult"
}
