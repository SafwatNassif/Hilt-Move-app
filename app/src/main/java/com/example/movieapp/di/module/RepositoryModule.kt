package com.example.movieapp.di.module

import com.example.movieapp.data.repository.MovieRepositoryImp
import com.example.movieapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMovieRepos(movieRepositoryImp: MovieRepositoryImp): MovieRepository


}