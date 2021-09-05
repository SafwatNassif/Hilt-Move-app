package com.example.movieapp.domain.useCase

import androidx.paging.PagingData
import com.example.movieapp.data.model.MovieItem
import kotlinx.coroutines.flow.Flow

interface MovieListUseCase {
    suspend fun getMovie(): Flow<PagingData<MovieItem>>
}