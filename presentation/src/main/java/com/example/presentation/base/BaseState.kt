package com.example.presentation.base

import com.example.domain.exception_handler.ErrorEntity

sealed class BaseState {
    object Empty : BaseState()
    object Loading : BaseState()
    object NotFound : BaseState()
    data class Error(val error: ErrorEntity) : BaseState()
}