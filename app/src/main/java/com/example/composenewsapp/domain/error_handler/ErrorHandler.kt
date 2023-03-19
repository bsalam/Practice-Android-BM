package com.example.composenewsapp.domain.error_handler

interface ErrorHandler {

    fun getError(throwable: Throwable): ErrorEntity
}