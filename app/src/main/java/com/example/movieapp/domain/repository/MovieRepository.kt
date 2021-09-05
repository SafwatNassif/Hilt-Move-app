package com.example.movieapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.movieapp.data.model.MovieItem
import com.example.movieapp.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovies(): Flow<PagingData<MovieItem>>

}