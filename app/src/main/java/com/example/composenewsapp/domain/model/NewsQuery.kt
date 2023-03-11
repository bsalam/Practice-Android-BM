package com.example.composenewsapp.domain.model

data class NewsQuery(
    val searchStatement: String,
    val pageNumber: Int = 1,
    val fromDate: String = "",
    val toDate: String = "",
    val language: String = ""
)
