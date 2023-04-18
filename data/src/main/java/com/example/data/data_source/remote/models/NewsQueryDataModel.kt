package com.example.data.data_source.remote.models

data class NewsQueryDataModel(
    val searchStatement: String,
    val pageNumber: Int,
    val fromDate: String,
    val toDate: String,
    val language: String
)
