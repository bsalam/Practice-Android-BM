package com.example.composenewsapp.domain.use_cases

import com.example.composenewsapp.domain.models.ArticleDomainModel
import com.example.composenewsapp.domain.models.NewsQuery
import com.example.composenewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class FetchNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(newsQuery: NewsQuery): List<ArticleDomainModel> {
        return repository.getNews(newsQuery)
    }
}