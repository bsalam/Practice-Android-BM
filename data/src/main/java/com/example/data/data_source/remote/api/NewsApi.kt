package com.example.data.data_source.remote.api

import com.example.data.data_source.remote.models.NewsResponseDataModel
import com.example.data.utils.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/everything")
    suspend fun getNews(
        @Query("q")
        searchStatement: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("from")
        fromDate: String = "",
        @Query("to")
        toDate: String = "",
        @Query("language")
        language: String = "",
    ) : NewsResponseDataModel

    @GET("/v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("q")
        searchStatement: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("country")
        countryCode: String = "",
//        @Query("category")
//        category: String = "",  // Allowed values ["business", "entertainment", "general", "health", "science", "sports", "technology"]
    ) : NewsResponseDataModel
}