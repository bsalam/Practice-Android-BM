package com.example.composenewsapp.di

import com.example.composenewsapp.data.data_source.remote.api.NewsApi
import com.example.composenewsapp.data.data_source.remote.api.RetrofitInstance
import com.example.composenewsapp.data.data_source.repository.NewsRepositoryImp
import com.example.composenewsapp.domain.repository.NewsRepository
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
        return RetrofitInstance.api;
    }

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi): NewsRepository {
        return NewsRepositoryImp(api);
    }
}