package com.example.composenewsapp.domain.exception_handler

sealed class CustomException : Exception() {

    object NoInternetConnectionException : CustomException()

    object TimeoutException : CustomException()

    object NetworkException : CustomException()

    object ServiceNotFoundException : CustomException()

    object AccessDeniedException : CustomException()

    object ServiceUnavailableException : CustomException()

    object UnknownException : CustomException()

    fun toError(): ErrorEntity {
        return when (this) {
            is NoInternetConnectionException -> ErrorEntity.NoInternetConnection
            is TimeoutException -> ErrorEntity.Timeout
            is NetworkException -> ErrorEntity.Network
            is ServiceNotFoundException, AccessDeniedException, ServiceUnavailableException ->
                ErrorEntity.ServiceUnreachable
            is UnknownException -> ErrorEntity.Unknown
        }
    }
}