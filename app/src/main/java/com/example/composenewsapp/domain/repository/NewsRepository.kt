package com.example.composenewsapp.domain.repository

import com.example.composenewsapp.domain.models.ArticleDomainModel
import com.example.composenewsapp.domain.models.BreakingNewsQuery
import com.example.composenewsapp.domain.models.NewsQuery
import com.example.composenewsapp.utils.Resource

interface NewsRepository {

    suspend fun getNews(newsQuery: NewsQuery) : Resource<List<ArticleDomainModel>>
    suspend fun getBreakingNews(breakingNewsQuery: BreakingNewsQuery) : Resource<List<ArticleDomainModel>>
}