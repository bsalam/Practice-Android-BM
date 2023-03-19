package com.example.composenewsapp.data.data_source.remote.models

data class NewsResponseDataModel(
    val status       : String,
    var totalResults : Int,
    var articles     : List<ArticleDataModel>
)
