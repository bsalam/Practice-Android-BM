package com.example.composenewsapp.utils

sealed class Resource<out T : Any> {
    data class Success<T : Any>(val data: T) : Resource<T>()
    data class Error<U : Any>(val message: UiText) : Resource<U>()
}