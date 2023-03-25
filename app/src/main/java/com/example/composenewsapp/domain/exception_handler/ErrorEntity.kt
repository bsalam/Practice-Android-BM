package com.example.composenewsapp.domain.exception_handler

sealed class ErrorEntity {

    object NoInternetConnection : ErrorEntity()

    object Network : ErrorEntity()

    object Timeout : ErrorEntity()

    object ServiceUnreachable : ErrorEntity()

    object Unknown : ErrorEntity()
}
