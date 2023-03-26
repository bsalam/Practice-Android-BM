package com.example.composenewsapp.presentation.base

import com.example.composenewsapp.domain.exception_handler.ErrorEntity
import com.example.composenewsapp.utils.UiText

sealed class BaseState {
    object Empty : BaseState()
    object Loading : BaseState()
    data class Error(val error: ErrorEntity) : BaseState()
    data class NoInternetConnection(val message: UiText) : BaseState()
}