package com.example.domain.models

data class BreakingNewsQueryDomainModel(
    val searchStatement: String,
    val pageNumber: Int = 1,
    val countryCode: String = "",
//    val category: String  // Allowed values ["business", "entertainment", "general", "health", "science", "sports", "technology"]
)
