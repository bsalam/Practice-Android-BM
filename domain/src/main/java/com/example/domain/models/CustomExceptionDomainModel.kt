package com.example.domain.models

sealed class CustomExceptionDomainModel : Exception() {

    object NoInternetConnectionExceptionDomainModel : CustomExceptionDomainModel()

    object TimeoutExceptionDomainModel : CustomExceptionDomainModel()

    object NetworkExceptionDomainModel : CustomExceptionDomainModel()

    object ServiceNotFoundExceptionDomainModel : CustomExceptionDomainModel()

    object AccessDeniedExceptionDomainModel : CustomExceptionDomainModel()

    object ServiceUnavailableExceptionDomainModel : CustomExceptionDomainModel()

    object UnknownExceptionDomainModel : CustomExceptionDomainModel()

}