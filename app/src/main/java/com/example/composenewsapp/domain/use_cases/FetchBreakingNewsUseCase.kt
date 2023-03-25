package com.example.composenewsapp.domain.use_cases

import com.example.composenewsapp.domain.models.ArticleDomainModel
import com.example.composenewsapp.domain.models.BreakingNewsQuery
import com.example.composenewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class FetchBreakingNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend operator fun invoke (breakingNewsQuery: BreakingNewsQuery): List<ArticleDomainModel> {
        return repository.getBreakingNews(breakingNewsQuery)
    }
}