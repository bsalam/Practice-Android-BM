package com.example.composenewsapp.di

import android.net.ConnectivityManager
import com.example.data.data_source.remote.api.NewsApi
import com.example.data.data_source.remote.api.RetrofitInstance
import com.example.data.exception_handler.ExceptionHandlerImpl
import com.example.data.repository.NewsRepositoryImpl
import com.example.domain.exception_handler.ExceptionHandler
import com.example.domain.repository.NewsRepository
import com.example.domain.use_cases.FetchNewsUseCase
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
    fun provideNewsApi(): NewsApi {
        return RetrofitInstance.api
    }

    @Provides
    @Singleton
    fun provideErrorHandler(): ExceptionHandler {
        return ExceptionHandlerImpl()
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        api: NewsApi,
        connectivityManager: ConnectivityManager,
        exceptionHandler: ExceptionHandler
    ): NewsRepository {
        return NewsRepositoryImpl(api, connectivityManager, exceptionHandler)
    }

    @Provides
    @Singleton
    fun provideNewsUseCase(newsRepository: NewsRepository): FetchNewsUseCase {
        return FetchNewsUseCase(newsRepository)
    }

}