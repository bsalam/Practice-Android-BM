package com.example.composenewsapp.presentation.news

import com.example.composenewsapp.domain.models.ArticleDomainModel
import com.example.composenewsapp.presentation.base.BaseState

data class NewsState(
    val news: List<ArticleDomainModel>? = null,
    val state: BaseState = BaseState.Empty
)