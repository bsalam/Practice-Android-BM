package com.example.composenewsapp.domain.use_cases

import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.domain.model.NewsQuery
import com.example.composenewsapp.domain.repository.NewsRepository
import com.example.composenewsapp.utils.Resource
import javax.inject.Inject

class FetchNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(newsQuery: NewsQuery): Resource<List<Article>> {
        return repository.getNews(newsQuery)
    }
}