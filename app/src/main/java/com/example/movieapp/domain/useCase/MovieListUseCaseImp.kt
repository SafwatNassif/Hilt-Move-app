package com.example.movieapp.domain.useCase

import com.example.movieapp.data.model.MovieItem
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieListUseCaseImp @Inject constructor(val movieRepository: MovieRepository) :
    MovieListUseCase {

    override suspend fun getMovie(): List<MovieItem> {
        val result = movieRepository.getMovies()
        return result.movies

    }

}