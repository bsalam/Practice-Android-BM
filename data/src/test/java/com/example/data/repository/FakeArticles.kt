package com.example.data.repository

import com.example.data.data_source.remote.models.ArticleDataModel
import com.example.domain.models.ArticleDomainModel

val fakeDataModelArticles = listOf(
    ArticleDataModel(
        title = "Test Article",
        description = "This is a test article",
        url = "https://example.com",
        urlToImage = "https://example.com/image.png",
        publishedAt = "13:00",
        author = "Mohamed"
    ),
    ArticleDataModel(
        title = "Test Article2",
        description = "This is a test article2",
        url = "https://example.com2",
        urlToImage = "https://example.com/image2.png",
        publishedAt = "01:00",
        author = "Samy"
    )
)

val fakeDomainModelArticles = listOf(
    ArticleDomainModel(
        title = "Test Article",
        description = "This is a test article",
        url = "https://example.com",
        urlToImage = "https://example.com/image.png",
        publishedAt = "13:00",
        author = "Mohamed"
    ),
    ArticleDomainModel(
        title = "Test Article2",
        description = "This is a test article2",
        url = "https://example.com2",
        urlToImage = "https://example.com/image2.png",
        publishedAt = "01:00",
        author = "Samy"
    )
)