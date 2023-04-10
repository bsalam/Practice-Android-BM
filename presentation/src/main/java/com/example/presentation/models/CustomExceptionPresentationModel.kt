package com.example.presentation.models

sealed class CustomExceptionPresentationModel {

    object NoInternetConnection : CustomExceptionPresentationModel()

    object Network : CustomExceptionPresentationModel()

    object Timeout : CustomExceptionPresentationModel()

    object ServiceUnreachable : CustomExceptionPresentationModel()

    object Unknown : CustomExceptionPresentationModel()

}
