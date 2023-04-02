package com.example.data.repository

import android.net.ConnectivityManager
import com.example.composenewsapp.data.mapper.toArticleDomainModel
import com.example.composenewsapp.data.data_source.remote.api.NewsApi
import com.example.composenewsapp.domain.exception_handler.CustomException
import com.example.composenewsapp.domain.exception_handler.ExceptionHandler
import com.example.composenewsapp.domain.models.ArticleDomainModel
import com.example.composenewsapp.domain.models.BreakingNewsQuery
import com.example.composenewsapp.domain.models.NewsQuery
import com.example.composenewsapp.domain.repository.NewsRepository
import com.example.composenewsapp.utils.Constants.Companion.SEARCH_NEWS_TIMEOUT
import com.example.composenewsapp.utils.isConnectedToInternet
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val connectivityManager: ConnectivityManager,
    private val exceptionHandler: ExceptionHandler
) : NewsRepository{

    override suspend fun getNews(
        newsQuery: NewsQuery
    ): List<ArticleDomainModel>  {
        if (!connectivityManager.isConnectedToInternet())
            throw CustomException.NoInternetConnectionException
        return try {
            withTimeout(SEARCH_NEWS_TIMEOUT){
                val response = api.getNews(
                    searchStatement = newsQuery.searchStatement,
                    pageNumber = newsQuery.pageNumber,
                    fromDate = newsQuery.fromDate,
                    toDate = newsQuery.toDate,
                    language = newsQuery.language
                )

                response.articles.map { it.toArticleDomainModel() }
            }
        } catch (e: Exception) {
            throw exceptionHandler.getCustomException(e)
        }
    }

    override suspend fun getBreakingNews(
        breakingNewsQuery: BreakingNewsQuery
    ): List<ArticleDomainModel> {
        if (!connectivityManager.isConnectedToInternet())
            throw CustomException.NoInternetConnectionException

        return try {
            withTimeout(SEARCH_NEWS_TIMEOUT){
                val response = api.getBreakingNews(
                    searchStatement = breakingNewsQuery.searchStatement,
                    pageNumber = breakingNewsQuery.pageNumber,
                    countryCode = breakingNewsQuery.countryCode
                )

                response.articles.map { it.toArticleDomainModel() }
            }
        } catch (e: Exception) {
            throw exceptionHandler.getCustomException(e)
        }

    }
}

/**
 * We can set timeouts settings on the underlying HTTP client. If we don’t specify a client,
 * Retrofit will create one with default connect and read timeouts. By default, Retrofit uses the following timeouts:
    - Connection timeout: 10 seconds
    - Read timeout: 10 seconds
    - Write timeout: 10 seconds
 **/