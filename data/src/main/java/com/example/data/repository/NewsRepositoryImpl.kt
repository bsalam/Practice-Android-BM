package com.example.data.repository

import android.net.ConnectivityManager
import com.example.data.data_source.remote.api.NewsApi
import com.example.data.mapper.toArticleDomainModel
import com.example.data.utils.Constants.Companion.SEARCH_NEWS_TIMEOUT
import com.example.data.utils.isConnectedToInternet
import com.example.domain.exception_handler.CustomException
import com.example.domain.exception_handler.ExceptionHandler
import com.example.domain.models.ArticleDomainModel
import com.example.domain.models.BreakingNewsQuery
import com.example.domain.models.NewsQuery
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val connectivityManager: ConnectivityManager,
    private val exceptionHandler: ExceptionHandler
) : NewsRepository {

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