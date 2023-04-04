package com.example.data.data_source.remote.models

data class ArticleDataModel(
    var source      : SourceDataModel,
    var author      : String?,
    var title       : String,
    var description : String,
    var url         : String,
    var urlToImage  : String?,
    var publishedAt : String,
    var content     : String
)
