package com.example.data.mapper

import com.example.data.data_source.remote.models.ArticleDataModel
import com.example.domain.models.ArticleDomainModel


fun ArticleDataModel.toArticleDomainModel(): ArticleDomainModel {
    return ArticleDomainModel(
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage ?: "",
        publishedAt = publishedAt,
        author = author ?: ""
    )
}