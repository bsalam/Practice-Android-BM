package com.example.composenewsapp.di

import android.net.ConnectivityManager
import com.example.data.data_source.remote.api.NewsApi
import com.example.data.data_source.remote.api.RetrofitInstance
import com.example.data.repository.NewsRepositoryImpl
import com.example.domain.repository.NewsRepository
import com.example.domain.use_cases.FetchNewsUseCase
import com.example.presentation.utils.DispatcherProvider
import com.example.presentation.utils.StandardDispatcher
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
    fun provideNewsRepository(
        api: NewsApi,
        connectivityManager: ConnectivityManager
    ): NewsRepository {
        return NewsRepositoryImpl(api, connectivityManager)
    }

    @Provides
    @Singleton
    fun provideNewsUseCase(newsRepository: NewsRepository): FetchNewsUseCase {
        return FetchNewsUseCase(newsRepository)
    }

    @Provides
    @Singleton
    fun provideStandardProvider(): DispatcherProvider {
        return StandardDispatcher()
    }

}