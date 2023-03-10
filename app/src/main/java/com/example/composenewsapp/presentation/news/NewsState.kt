package com.example.composenewsapp.presentation.news

import com.example.composenewsapp.presentation.base.BaseState

data class NewsState(
    val news: List<String>? = null,
    val baseState: BaseState = BaseState.Empty
)