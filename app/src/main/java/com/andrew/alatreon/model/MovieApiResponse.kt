package com.andrew.alatreon.model

import com.google.gson.annotations.SerializedName

/** This data class is dealing with the response received by TMDB API from popular, topRated and
 * upcoming services.
 */
data class MovieApiResponse(

    @SerializedName("page")
    val page: Int? = 0,
    @SerializedName("results")
    val results: List<Movie> = arrayListOf(),
    @SerializedName("total_pages")
    val totalPages: Int? = 0,
    @SerializedName("total_results")
    val totalResults: Int? = 0

) {
    override fun toString() = "page: $page, results: $results, totalPages: $totalPages, " +
            "totalResults: $totalResults"
}
