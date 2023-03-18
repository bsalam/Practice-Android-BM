package com.example.composenewsapp.data.data_source.mapper

import com.example.composenewsapp.data.data_source.remote.dto.ArticleDto
import com.example.composenewsapp.domain.model.Article


fun ArticleDto.toArticle(): Article {
    return Article(
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage ?: "",
        publishedAt = publishedAt,
        author = author ?: "Seif"
    )
}