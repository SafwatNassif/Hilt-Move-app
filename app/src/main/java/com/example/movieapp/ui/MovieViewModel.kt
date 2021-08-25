package com.example.movieapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.model.MovieItem
import com.example.movieapp.domain.useCase.MovieListUseCase
import com.example.movieapp.ui.movieList.MovieListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    val movieListUseCase: MovieListUseCase
) : ViewModel() {

    val viewState = MutableLiveData<MovieListViewState>()

    val handleException = CoroutineExceptionHandler { coroutineContext, throwable ->
        when (throwable) {
            is HttpException -> {
                if (throwable.code() in 400 until 500) {
                    //print message
                } else if (throwable.code() in 500 until 600) {
                    //print server down
                } else {
                    // un known error
                }
            }
            is IOException -> {
                Log.e("MovieViewModel", "please check internet connection : ${throwable.message}")
            }
        }
    }

    fun getMovieList() {
        viewState.value = MovieListViewState.Loading(true)

        viewModelScope.launch(Dispatchers.IO) {
            supervisorScope {
                var movieDes: List<MovieItem>? = null
                var movieR: List<MovieItem>? = null

                val moviesWithVoteAverageDesc = async {
                    movieListUseCase.getMoviesWithVoteAverageDesc()
                }
                val movies = async {
                    movieListUseCase.getMovie()
                }

                try {
                    movieDes = moviesWithVoteAverageDesc.await()
                } catch (e: Exception) {
                    handleException.handleException(this.coroutineContext, e)
                }

                try {
                    movieR = movies.await()

                } catch (e: Exception) {
                    handleException.handleException(this.coroutineContext, e)
                }

                withContext(Dispatchers.Main) {
                    viewState.value = MovieListViewState.Loading(false)
                    viewState.value = movieR?.let { MovieListViewState.Success(it) }
                }
            }
        }
    }


}