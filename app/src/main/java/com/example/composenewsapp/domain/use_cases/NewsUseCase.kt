package com.example.composenewsapp.domain.use_cases

import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.domain.model.NewsQuery
import com.example.composenewsapp.domain.repository.NewsRepository
import com.example.composenewsapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend operator fun invoke (newsQuery: NewsQuery) : Flow<Resource<List<Article>>> {
        return repository.getNews(newsQuery)
    }

}