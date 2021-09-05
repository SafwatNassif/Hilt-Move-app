package com.example.movieapp.ui.movieList

import androidx.paging.PagingData
import com.example.movieapp.data.model.MovieItem

sealed class MovieListViewState {
    class Loading(val show: Boolean) : MovieListViewState()
    class Success(val movies: PagingData<MovieItem> = PagingData.empty()) : MovieListViewState()
}