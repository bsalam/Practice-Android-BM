package com.example.composenewsapp.presentation.news

import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.presentation.base.BaseState

data class NewsState(
    val news: List<Article> = emptyList(),
    val baseState: BaseState = BaseState.Empty
)