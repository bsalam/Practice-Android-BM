package com.example.domain.models

data class NewsQueryDomainModel(
    val searchStatement: String,
    val pageNumber: Int = 1,
    val fromDate: String = "",
    val toDate: String = "",
    val language: String = ""
)
