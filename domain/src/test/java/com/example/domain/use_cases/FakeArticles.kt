package com.example.domain.use_cases

import com.example.domain.models.ArticleDomainModel

val fakeArticles = listOf(
    ArticleDomainModel(
        title = "Test Article",
        description = "This is a test article",
        url = "https://example.com",
        urlToImage = "https://example.com/image.png",
        publishedAt = "13:00",
        author = "Seif"
    ),
    ArticleDomainModel(
        title = "Test Article2",
        description = "This is a test article2",
        url = "https://example.com2",
        urlToImage = "https://example.com/image2.png",
        publishedAt = "12:00",
        author = "Seif Mohamed"
    )
)