package com.example.composenewsapp.data.exception_handler

import com.example.composenewsapp.domain.exception_handler.CustomException
import com.example.composenewsapp.domain.exception_handler.ExceptionHandler
import kotlinx.coroutines.TimeoutCancellationException
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class ExceptionHandlerImpl : ExceptionHandler {

    override fun getCustomException(throwable: Throwable): CustomException {
        return when(throwable) {
            is IOException -> CustomException.NetworkException
            is TimeoutCancellationException -> CustomException.TimeoutException
            is IllegalStateException -> CustomException.NewsNotFoundException
            is HttpException -> {
                when(throwable.code()) {

                    HttpURLConnection.HTTP_NOT_FOUND -> CustomException.ServiceNotFoundException

                    HttpURLConnection.HTTP_FORBIDDEN -> CustomException.AccessDeniedException

                    HttpURLConnection.HTTP_UNAVAILABLE -> CustomException.ServiceUnavailableException

                    else -> CustomException.UnknownException
                }
            }
            else -> CustomException.UnknownException
        }
    }
}