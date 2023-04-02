package com.example.composenewsapp.presentation.base

import com.example.composenewsapp.domain.exception_handler.ErrorEntity

sealed class BaseState {
    object Empty : BaseState()
    object Loading : BaseState()
    object NotFound : BaseState()
    data class Error(val error: ErrorEntity) : BaseState()
}