package com.example.composenewsapp.domain.exception_handler

interface ExceptionHandler {

    fun getCustomException(throwable: Throwable): CustomException
}