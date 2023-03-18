package com.example.composenewsapp.data.data_source.repository

import android.util.Log
import com.example.composenewsapp.data.data_source.mapper.toArticle
import com.example.composenewsapp.data.data_source.remote.api.NewsApi
import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.domain.model.BreakingNewsQuery
import com.example.composenewsapp.domain.model.NewsQuery
import com.example.composenewsapp.domain.repository.NewsRepository
import com.example.composenewsapp.utils.Resource
import com.example.composenewsapp.utils.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val api: NewsApi
) : NewsRepository{
    override suspend fun getNews(
        newsQuery: NewsQuery
    ): Resource<List<Article>>  {
        return try {
            val articles = api.getNews(
                searchStatement = newsQuery.searchStatement,
                pageNumber = newsQuery.pageNumber,
                fromDate = newsQuery.fromDate,
                toDate = newsQuery.toDate,
                language = newsQuery.language
            )
            Log.d("---", "${articles.articles}")
            Resource.Success(articles.articles.map { it.toArticle() })
        } catch (e: Exception) {
            Log.d("---", "Error: ${e.message}")
            Resource.Error(UiText.DynamicString(e.message.toString()))
        }
    }

    override suspend fun getBreakingNews(
        breakingNewsQuery: BreakingNewsQuery
    ): Flow<Resource<List<Article>>> = flow {
        try {
           // emit(Resource.Loading())

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