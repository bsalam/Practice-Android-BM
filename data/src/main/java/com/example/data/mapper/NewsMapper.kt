package com.example.data.mapper

import com.example.data.data_source.remote.models.ArticleDataModel
import com.example.domain.models.CustomExceptionDomainModel
import com.example.domain.models.ArticleDomainModel
import retrofit2.HttpException
import java.io.IOException
import java.io.InterruptedIOException
import java.net.HttpURLConnection

fun ArticleDataModel.toArticleDomainModel(): ArticleDomainModel {
    return ArticleDomainModel(
        title = title ?: "",
        description = description ?: "",
        url = url ?: "",
        urlToImage = urlToImage ?: "",
        publishedAt = publishedAt ?: "",
        author = author ?: ""
    )
}

fun Throwable.toCustomExceptionDomainModel(): CustomExceptionDomainModel {
    return when(this) {
        is InterruptedIOException -> CustomExceptionDomainModel.TimeoutExceptionDomainModel
        is IOException -> CustomExceptionDomainModel.NetworkExceptionDomainModel
        is HttpException -> {
            when(this.code()) {

                HttpURLConnection.HTTP_NOT_FOUND -> CustomExceptionDomainModel.ServiceNotFoundExceptionDomainModel

                HttpURLConnection.HTTP_FORBIDDEN -> CustomExceptionDomainModel.AccessDeniedExceptionDomainModel

                HttpURLConnection.HTTP_UNAVAILABLE -> CustomExceptionDomainModel.ServiceUnavailableExceptionDomainModel

                else -> CustomExceptionDomainModel.UnknownExceptionDomainModel
            }
        }
        else -> CustomExceptionDomainModel.UnknownExceptionDomainModel
    }
}