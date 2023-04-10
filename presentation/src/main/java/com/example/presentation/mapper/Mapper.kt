package com.example.presentation.mapper

import com.example.domain.models.CustomExceptionDomainModel
import com.example.presentation.models.CustomExceptionPresentationModel

fun CustomExceptionDomainModel.toCustomExceptionPresentationModel(): CustomExceptionPresentationModel {
    return when (this) {
        is CustomExceptionDomainModel.NoInternetConnectionExceptionDomainModel -> CustomExceptionPresentationModel.NoInternetConnection
        is CustomExceptionDomainModel.TimeoutExceptionDomainModel -> CustomExceptionPresentationModel.Timeout
        is CustomExceptionDomainModel.NetworkExceptionDomainModel -> CustomExceptionPresentationModel.Network
        is CustomExceptionDomainModel.ServiceNotFoundExceptionDomainModel, CustomExceptionDomainModel.AccessDeniedExceptionDomainModel, CustomExceptionDomainModel.ServiceUnavailableExceptionDomainModel ->
            CustomExceptionPresentationModel.ServiceUnreachable
        is CustomExceptionDomainModel.UnknownExceptionDomainModel -> CustomExceptionPresentationModel.Unknown
    }
}