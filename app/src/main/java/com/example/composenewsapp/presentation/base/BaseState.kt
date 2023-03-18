package com.example.composenewsapp.presentation.base

import com.example.composenewsapp.utils.UiText

sealed class BaseState {
    object Empty : BaseState()
    object Loading : BaseState()
    data class Error(val errorMessage: UiText) : BaseState()
    data class NoInternetConnection(val message: UiText) : BaseState()
}
data class MyState (
    val baseState: BaseState = BaseState.Empty
)