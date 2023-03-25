package com.example.composenewsapp.domain.repository

import com.example.composenewsapp.domain.models.ArticleDomainModel
import com.example.composenewsapp.domain.models.BreakingNewsQuery
import com.example.composenewsapp.domain.models.NewsQuery

interface NewsRepository {

    suspend fun getNews(newsQuery: NewsQuery) : List<ArticleDomainModel>
    suspend fun getBreakingNews(breakingNewsQuery: BreakingNewsQuery) : List<ArticleDomainModel>
}