package com.example.data.data_source.remote.models

data class BreakingNewsQueryDataModel(
    val searchStatement: String,
    val pageNumber: Int,
    val countryCode: String,
//    val category: String  // Allowed values ["business", "entertainment", "general", "health", "science", "sports", "technology"]
)