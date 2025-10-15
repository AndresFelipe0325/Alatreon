package com.andrew.alatreon.network

import com.andrew.alatreon.model.MovieApiResponse
import com.andrew.alatreon.model.MovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): Response<MovieApiResponse>

    @GET("/3/movie/upcoming")
    suspend fun getUpcomingMovies(): Response<MovieApiResponse>

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(): Response<MovieApiResponse>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): Response<MovieDetail>

}