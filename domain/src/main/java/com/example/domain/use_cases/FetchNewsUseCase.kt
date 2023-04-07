package com.example.domain.use_cases

import com.example.domain.models.ArticleDomainModel
import com.example.domain.models.NewsQueryDomainModel
import com.example.domain.repository.NewsRepository

class FetchNewsUseCase (
    private val repository: NewsRepository
) {
    suspend operator fun invoke(newsQueryDomainModel: NewsQueryDomainModel): List<ArticleDomainModel> {
        return repository.getNews(newsQueryDomainModel)
    }
}