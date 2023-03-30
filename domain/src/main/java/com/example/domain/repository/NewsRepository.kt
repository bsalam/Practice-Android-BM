package com.example.domain.repository

import com.example.domain.models.ArticleDomainModel
import com.example.domain.models.BreakingNewsQuery
import com.example.domain.models.NewsQuery

interface NewsRepository {
    suspend fun getNews(newsQuery: NewsQuery) : List<ArticleDomainModel>
    suspend fun getBreakingNews(breakingNewsQuery: BreakingNewsQuery) : List<ArticleDomainModel>
}