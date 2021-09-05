package com.example.movieapp.domain.useCase

import androidx.paging.PagingData
import com.example.movieapp.data.model.MovieItem
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListUseCaseImp @Inject constructor(val movieRepository: MovieRepository) :
    MovieListUseCase {

    override suspend fun getMovie(): Flow<PagingData<MovieItem>> {
        return movieRepository.getMovies()
    }

}