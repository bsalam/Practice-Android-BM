package com.example.composenewsapp.data.data_source.repository

import com.example.composenewsapp.R
import com.example.composenewsapp.data.data_source.mapper.toArticle
import com.example.composenewsapp.data.data_source.remote.api.NewsApi
import com.example.composenewsapp.data.data_source.remote.dto.NewsResponse
import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.domain.model.BreakingNewsQuery
import com.example.composenewsapp.domain.model.NewsQuery
import com.example.composenewsapp.domain.repository.NewsRepository
import com.example.composenewsapp.utils.Resource
import com.example.composenewsapp.utils.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val api: NewsApi
) : NewsRepository{

    override suspend fun getNews(
        newsQuery: NewsQuery
    ): Flow<Resource<List<Article>>> = flow {
        try {
            emit(Resource.Loading())

            val articles = api.getNews(
                searchStatement = newsQuery.searchStatement,
                pageNumber = newsQuery.pageNumber,
                fromDate = newsQuery.fromDate,
                toDate = newsQuery.toDate,
                language = newsQuery.language
            ).articles.map { it.toArticle() }

            emit(Resource.Success(articles))

        } catch (e: Exception) {

            emit(Resource.Error(UiText.DynamicString(e.message ?: "Unexpected Exception!")))
        }

    }

    override suspend fun getBreakingNews(
        breakingNewsQuery: BreakingNewsQuery
    ): Flow<Resource<List<Article>>> = flow {
        try {
            emit(Resource.Loading())

            val articles = api.getBreakingNews(
                searchStatement = breakingNewsQuery.searchStatement,
                pageNumber = breakingNewsQuery.pageNumber,
                countryCode = breakingNewsQuery.countryCode
            ).articles.map { it.toArticle() }

            emit(Resource.Success(articles))

        } catch (e: Exception) {

            emit(Resource.Error(UiText.DynamicString(e.message ?: "Unexpected Exception!")))
        }

    }
}