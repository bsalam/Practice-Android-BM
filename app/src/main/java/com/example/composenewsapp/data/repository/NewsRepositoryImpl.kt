package com.example.composenewsapp.data.repository

import android.util.Log
import com.example.composenewsapp.data.mapper.toArticleDomainModel
import com.example.composenewsapp.data.data_source.remote.api.NewsApi
import com.example.composenewsapp.domain.error_handler.ErrorHandler
import com.example.composenewsapp.domain.models.ArticleDomainModel
import com.example.composenewsapp.domain.models.BreakingNewsQuery
import com.example.composenewsapp.domain.models.NewsQuery
import com.example.composenewsapp.domain.repository.NewsRepository
import com.example.composenewsapp.utils.Resource
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val errorHandler: ErrorHandler
) : NewsRepository{
    override suspend fun getNews(
        newsQuery: NewsQuery
    ): Resource<List<ArticleDomainModel>>  {
        return try {

            val response = api.getNews(
                searchStatement = newsQuery.searchStatement,
                pageNumber = newsQuery.pageNumber,
                fromDate = newsQuery.fromDate,
                toDate = newsQuery.toDate,
                language = newsQuery.language
            )
            Log.d("---", "${response.articles}")
            Resource.Success(response.articles.map { it.toArticleDomainModel() })

        } catch (e: Exception) {
            Log.d("---", "Error: ${e.message}")
            Resource.Error(errorHandler.getError(e))
        }
    }

    override suspend fun getBreakingNews(
        breakingNewsQuery: BreakingNewsQuery
    ): Resource<List<ArticleDomainModel>> {
        return try {

            val response = api.getBreakingNews(
                searchStatement = breakingNewsQuery.searchStatement,
                pageNumber = breakingNewsQuery.pageNumber,
                countryCode = breakingNewsQuery.countryCode
            )

            Resource.Success(response.articles.map { it.toArticleDomainModel() })

        } catch (e: Exception) {

            Resource.Error(errorHandler.getError(e))
        }

    }
}