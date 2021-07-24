package com.example.movieapp.di.module

import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.useCase.MovieListUseCase
import com.example.movieapp.domain.useCase.MovieListUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UserCaseModule {

    @Provides
    @ViewModelScoped
    fun provideMovieListUseCase(repository: MovieRepository): MovieListUseCase {
        return MovieListUseCaseImp(repository)
    }


}