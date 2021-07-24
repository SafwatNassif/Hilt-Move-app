package com.example.movieapp.domain.useCase

import com.example.movieapp.data.model.MovieItem

interface MovieListUseCase {
    suspend fun getMovie(): List<MovieItem>
}