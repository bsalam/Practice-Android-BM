package com.example.composenewsapp.di

import com.example.composenewsapp.data.data_source.remote.api.NewsApi
import com.example.composenewsapp.data.data_source.remote.api.RetrofitInstance
import com.example.composenewsapp.data.error_handler.ErrorHandlerImpl
import com.example.composenewsapp.data.repository.NewsRepositoryImpl
import com.example.composenewsapp.domain.error_handler.ErrorHandler
import com.example.composenewsapp.domain.repository.NewsRepository
import com.example.composenewsapp.domain.use_cases.FetchNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi{
        return RetrofitInstance.api
    }

    @Provides
    @Singleton
    fun provideErrorHandler(): ErrorHandler {
        return ErrorHandlerImpl()
    }

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi, errorHandler: ErrorHandler): NewsRepository {
        return NewsRepositoryImpl(api, errorHandler)
    }

    @Provides
    @Singleton
    fun provideNewsUseCase(newsRepository: NewsRepository): FetchNewsUseCase {
        return FetchNewsUseCase(newsRepository)
    }

}