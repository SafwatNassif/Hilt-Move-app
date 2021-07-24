package com.example.movieapp.data.repository

import com.example.movieapp.data.model.MovieItem
import com.example.movieapp.data.model.MovieResponse
import com.example.movieapp.data.remote.MovieAPI
import com.example.movieapp.domain.repository.MovieRepository
import java.lang.Exception
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(val remoteDataSource: MovieAPI) : MovieRepository {

    override suspend fun getMovies(): MovieResponse {
        return remoteDataSource.getMovieList()
    }


}