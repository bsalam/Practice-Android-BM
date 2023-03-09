package com.example.composenewsapp.presentation.base

sealed class BaseState {
    object Empty : BaseState()
    object Loading : BaseState()
    data class Error(val errorMessage: String) : BaseState()
    data class NoInternetConnection(val message: String) : BaseState()
}