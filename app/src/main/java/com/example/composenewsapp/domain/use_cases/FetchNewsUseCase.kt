package com.example.composenewsapp.domain.use_cases

import android.util.Log
import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.domain.model.NewsQuery
import com.example.composenewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class FetchNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(newsQuery: NewsQuery): List<Article> {
        return try {
            repository.getNews(newsQuery)
        }
        catch (e:Exception){
            Log.d("---", "usecase:"+ e.message.toString())
            throw e
        }
    }
}