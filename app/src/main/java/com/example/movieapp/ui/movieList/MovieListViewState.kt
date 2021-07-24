package com.example.movieapp.ui.movieList

import com.example.movieapp.data.model.MovieItem

sealed class MovieListViewState {
    class Loading(val show: Boolean) : MovieListViewState()
    class Success(val movies: List<MovieItem>) : MovieListViewState()
}