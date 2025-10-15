package com.andrew.alatreon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrew.alatreon.model.Movie
import com.andrew.alatreon.model.MovieDetail
import com.andrew.alatreon.network.MovieApi
import com.andrew.alatreon.network.MovieService
import com.andrew.alatreon.util.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel: ViewModel(), Logger {
    // Declaring the retrofit service
    private var retrofitService: MovieApi = MovieService.getInstance()
        .create(MovieApi::class.java)
    // Declaring the liveData with encapsulation strategy
    private var _popularMovies = MutableLiveData<ArrayList<Movie>>()
    val popularMovies: LiveData<ArrayList<Movie>>
        get() = _popularMovies
    private var _upcomingMovies = MutableLiveData<ArrayList<Movie>>()
    val upcomingMovies: LiveData<ArrayList<Movie>>
        get() = _upcomingMovies
    private var _topRatedMovies = MutableLiveData<ArrayList<Movie>>()
    val topRatedMovies: LiveData<ArrayList<Movie>>
        get() = _topRatedMovies
    private var _movieDetails = MutableLiveData<MovieDetail>()
    val movieDetails: LiveData<MovieDetail>
        get() = _movieDetails
    private var _errorMessage = MutableLiveData<Boolean>()
    val errorLoading: LiveData<Boolean>
        get() = _errorMessage
    private var _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState




    /** Methods to get information about movies through the Movies Database API:
     * popular, upcoming, top rated and movie details.
     **/
    fun getPopularMovies() {
        logInfo("Goes here")
        _loadingState.value = true
        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                retrofitService.getPopularMovies()
            }
            if(results.isSuccessful) {
                _popularMovies.value = results.body()?.results
                _loadingState.value = false
                _errorMessage.value = false
                logInfo(popularMovies.value.toString())
            }
            else {
                logError(results.errorBody().toString())
                _popularMovies.value = arrayListOf()
                _loadingState.value = false
                _errorMessage.value = true
            }
        }
    }

    fun getUpcomingMovies() {
        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                retrofitService.getUpcomingMovies()
            }
            if (results.isSuccessful) {
                _upcomingMovies.value = results.body()?.results
                _loadingState.value = false
                _errorMessage.value = false
            }
            else {
                _upcomingMovies.value = arrayListOf()
                _loadingState.value = false
                _errorMessage.value = true
            }

        }
    }

    fun getTopRatedMovies() {
        _loadingState.value = true
        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                retrofitService.getTopRatedMovies()
            }
            if(results.isSuccessful) {
                _topRatedMovies.value = results.body()?.results
                _loadingState.value = false
                _errorMessage.value = false
            }
            else {
                _topRatedMovies.value = arrayListOf()
                _loadingState.value = false
                _errorMessage.value = true
            }
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