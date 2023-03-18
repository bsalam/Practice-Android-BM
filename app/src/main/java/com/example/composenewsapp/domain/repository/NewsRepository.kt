package com.example.composenewsapp.domain.repository

import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.domain.model.BreakingNewsQuery
import com.example.composenewsapp.domain.model.NewsQuery
import com.example.composenewsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(newsQuery: NewsQuery) : Resource<List<Article>>
    suspend fun getBreakingNews(breakingNewsQuery: BreakingNewsQuery) : Flow<Resource<List<Article>>>
}