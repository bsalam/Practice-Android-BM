package com.example.data.repository

import android.net.ConnectivityManager
import com.example.data.data_source.remote.api.NewsApi
import com.example.data.mapper.toArticleDomainModel
import com.example.data.mapper.toBreakingNewsQueryDataModel
import com.example.data.mapper.toCustomExceptionDomainModel
import com.example.data.mapper.toNewsQueryDataModel
import com.example.data.utils.isConnectedToInternet
import com.example.domain.models.CustomExceptionDomainModel
import com.example.domain.models.ArticleDomainModel
import com.example.domain.models.BreakingNewsQueryDomainModel
import com.example.domain.models.NewsQueryDomainModel
import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val connectivityManager: ConnectivityManager,
) : NewsRepository {

    override suspend fun getNews(
        newsQueryDomainModel: NewsQueryDomainModel
    ): List<ArticleDomainModel>  {
        if (!connectivityManager.isConnectedToInternet())
            throw CustomExceptionDomainModel.NoInternetConnectionExceptionDomainModel
        return try {
            val query = newsQueryDomainModel.toNewsQueryDataModel()

            val response = api.getNews(
                searchStatement = query.searchStatement,
                pageNumber = query.pageNumber,
                fromDate = query.fromDate,
                toDate = query.toDate,
                language = query.language
            )

            response.articles?.map { it.toArticleDomainModel() } ?: emptyList()

        } catch (e: Exception) {
            throw e.toCustomExceptionDomainModel()
        }
    }

    override suspend fun getBreakingNews(
        breakingNewsQueryDomainModel: BreakingNewsQueryDomainModel
    ): List<ArticleDomainModel> {
        if (!connectivityManager.isConnectedToInternet())
            throw CustomExceptionDomainModel.NoInternetConnectionExceptionDomainModel

        return try {
            val query = breakingNewsQueryDomainModel.toBreakingNewsQueryDataModel()

            val response = api.getBreakingNews(
                searchStatement = query.searchStatement,
                pageNumber = query.pageNumber,
                countryCode = query.countryCode
            )

            response.articles?.map { it.toArticleDomainModel() } ?: emptyList()

        } catch (e: Exception) {
            throw e.toCustomExceptionDomainModel()
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