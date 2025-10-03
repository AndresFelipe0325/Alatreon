package com.andrew.alatreon.model

/** This data class is dealing with the response received by TMDB API from popular, topRated and
 * upcoming services.
 */
data class MovieApiResponse(
    val page: Int? = 0,
    val results: List<Movie> = arrayListOf(),
    val totalPages: Int? = 0,
    val totalResults: Int? = 0
) {
    override fun toString() = "page: $page, results: $results, totalPages: $totalPages, " +
            "totalResults: $totalResults"
}
