package com.andrew.alatreon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrew.alatreon.model.Movie
import com.andrew.alatreon.model.MovieDetail
import com.andrew.alatreon.network.MovieApi
import com.andrew.alatreon.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class MainActivityViewModel: ViewModel() {
    private var retrofitService: MovieApi = RetrofitService.getInstance()
        .create(MovieApi::class.java)

    private var _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>>
        get() = _popularMovies
    private var _upcomingMovies = MutableLiveData<List<Movie>>()
    val upcomingMovies: LiveData<List<Movie>>
        get() = _upcomingMovies
    private var _topRatedMovies = MutableLiveData<List<Movie>>()
    val topRatedMovies: LiveData<List<Movie>>
        get() = _topRatedMovies
    private var _movieDetails = MutableLiveData<MovieDetail>()
    val movieDetails: LiveData<MovieDetail>
        get() = _movieDetails



    fun getPopularMovies() {
        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                retrofitService.getPopularMovies()
            }
            _popularMovies.value = results.body()?.results
        }
    }

    fun getUpcomingMovies() {
        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                retrofitService.getUpcomingMovies()
            }
            _upcomingMovies.value = results.body()?.results
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                retrofitService.getTopRatedMovies()
            }
            _topRatedMovies.value = results.body()?.results
        }
    }

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                retrofitService.getMovieDetails(id)
            }
            _movieDetails.value = results.body()
        }
    }
}