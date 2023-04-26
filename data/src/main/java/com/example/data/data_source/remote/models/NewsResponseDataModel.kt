package com.example.data.data_source.remote.models

data class NewsResponseDataModel(
    val status       : String? = null,
    var totalResults : Int? = null,
    var articles     : List<ArticleDataModel>? = null
)
