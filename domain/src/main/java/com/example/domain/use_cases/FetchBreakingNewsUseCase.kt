package com.example.domain.use_cases

import com.example.domain.models.ArticleDomainModel
import com.example.domain.models.BreakingNewsQuery
import com.example.domain.repository.NewsRepository

class FetchBreakingNewsUseCase (
    private val repository: NewsRepository
) {

    suspend operator fun invoke (breakingNewsQuery: BreakingNewsQuery): List<ArticleDomainModel> {
        return repository.getBreakingNews(breakingNewsQuery)
    }
}