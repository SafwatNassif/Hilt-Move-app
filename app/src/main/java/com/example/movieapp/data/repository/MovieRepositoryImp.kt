package com.example.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.data.dataSource.MovieDataSource
import com.example.movieapp.data.model.MovieItem
import com.example.movieapp.data.remote.MovieAPI
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(private val remoteDataSource: MovieAPI) :
    MovieRepository {

    override suspend fun getMovies(): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { MovieDataSource(remoteDataSource) }
        ).flow
    }


}