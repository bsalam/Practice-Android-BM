package com.example.composenewsapp.presentation.news


data class NewsArticle(
    val publishedAt: String,

    val source: NewsSource,

    val title: String,

    val description: String? = null,

    val url: String? = null,

    val urlToImage: String? = null
)

data class NewsSource(
    val name: String?
)
