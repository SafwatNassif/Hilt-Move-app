package com.example.movieapp.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.model.MovieItem
import com.example.movieapp.data.remote.MovieAPI

class MovieDataSource(private val movieAPI: MovieAPI) : PagingSource<Int, MovieItem>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        val position = params.key ?: 1
        return try {
            val response = movieAPI.getMovieList(page = position)
            LoadResult.Page(
                data = response.movies,
                prevKey = if (position == 0) null else position - 1,
                nextKey = if (response.movies.isEmpty()) null else position + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}