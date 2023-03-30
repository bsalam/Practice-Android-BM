package com.example.composenewsapp.di

import com.example.domain.repository.NewsRepository
import com.example.domain.use_cases.FetchBreakingNewsUseCase
import com.example.domain.use_cases.FetchNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideFetchNewsUseCase(newsRepository: NewsRepository): FetchNewsUseCase {
        return FetchNewsUseCase(newsRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideFetchBreakingNewsUseCase(newsRepository: NewsRepository): FetchBreakingNewsUseCase {
        return FetchBreakingNewsUseCase(newsRepository)
    }
}