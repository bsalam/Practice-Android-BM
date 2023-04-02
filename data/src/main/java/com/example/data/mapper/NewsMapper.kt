package com.example.data.mapper

import com.example.composenewsapp.data.data_source.remote.models.ArticleDataModel
import com.example.composenewsapp.domain.models.ArticleDomainModel


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