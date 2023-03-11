package com.example.composenewsapp.domain.model

data class BreakingNewsQuery(
    val searchStatement: String,
    val pageNumber: Int = 1,
    val countryCode: String = "",
//    val category: String  // Allowed values ["business", "entertainment", "general", "health", "science", "sports", "technology"]
)
