package com.example.domain.repository

import com.example.domain.models.ArticleDomainModel
import com.example.domain.models.BreakingNewsQueryDomainModel
import com.example.domain.models.NewsQueryDomainModel

interface NewsRepository {
    suspend fun getNews(newsQueryDomainModel: NewsQueryDomainModel) : List<ArticleDomainModel>
    suspend fun getBreakingNews(breakingNewsQueryDomainModel: BreakingNewsQueryDomainModel) : List<ArticleDomainModel>
}