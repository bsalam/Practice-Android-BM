package com.example.domain.exception_handler

interface ExceptionHandler {

    fun getCustomException(throwable: Throwable): CustomException
}