package com.example.domain.use_cases

import com.example.domain.models.ArticleDomainModel
import com.example.domain.models.BreakingNewsQueryDomainModel
import com.example.domain.repository.NewsRepository

class FetchBreakingNewsUseCase (
    private val repository: NewsRepository
) {

    suspend operator fun invoke (breakingNewsQueryDomainModel: BreakingNewsQueryDomainModel): List<ArticleDomainModel> {
        return repository.getBreakingNews(breakingNewsQueryDomainModel)
    }
}