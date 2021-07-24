package com.example.movieapp.data.remote

import com.example.movieapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("discover/movie")
    suspend fun getMovieList(@Query("sort_by") sortedBy: String = "popularity.desc"): MovieResponse

}