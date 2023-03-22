package com.example.composenewsapp.data.data_source.repository

import android.util.Log
import com.example.composenewsapp.data.data_source.mapper.toArticle
import com.example.composenewsapp.data.data_source.remote.api.NewsApi
import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.domain.model.BreakingNewsQuery
import com.example.composenewsapp.domain.model.NewsQuery
import com.example.composenewsapp.domain.repository.NewsRepository
import com.example.composenewsapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val api: NewsApi
) : NewsRepository {
    override suspend fun getNews(
        newsQuery: NewsQuery
    ): List<Article> {
        Log.d("---", "getNews in Repo")
        return try {
             api.getNews(
                searchStatement = newsQuery.searchStatement,
                pageNumber = newsQuery.pageNumber,
                fromDate = newsQuery.fromDate,
                toDate = newsQuery.toDate,
                language = newsQuery.language
            ).articles.map { it.toArticle() }
            // Log.d("---", "${articles.articles}")
           // Resource.Success(articles.articles.map { it.toArticle() })
        } catch (e: Exception) {
            Log.d("---", e.message.toString())
            throw e
        }

    }

    override suspend fun getBreakingNews(
        breakingNewsQuery: BreakingNewsQuery
    ): Flow<Resource<List<Article>>> = flow {
        try {
            val articles = api.getBreakingNews(
                searchStatement = breakingNewsQuery.searchStatement,
                pageNumber = breakingNewsQuery.pageNumber,
                countryCode = breakingNewsQuery.countryCode
            ).articles.map { it.toArticle() }
            emit(Resource.Success(articles))
        } catch (e: Exception) {

            emit(Resource.Error(e))
        }
    }
}