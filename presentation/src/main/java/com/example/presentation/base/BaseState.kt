package com.example.presentation.base

import com.example.presentation.models.CustomExceptionPresentationModel

sealed class BaseState {
    object Empty : BaseState()
    object Loading : BaseState()
    data class Error(val error: CustomExceptionPresentationModel) : BaseState()
}