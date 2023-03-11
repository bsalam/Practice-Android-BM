package com.example.composenewsapp.data.data_source.remote.dto

data class NewsResponse(
    val status       : String,
    var totalResults : Int,
    var articles     : List<ArticleDto>
)
