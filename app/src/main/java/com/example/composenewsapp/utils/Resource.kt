package com.example.composenewsapp.utils

import com.example.composenewsapp.domain.error_handler.ErrorEntity

sealed class Resource<out T : Any> {
    data class Success<T : Any>(val data: T) : Resource<T>()
    data class Error<T : Any>(val error: ErrorEntity) : Resource<T>()
}