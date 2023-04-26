package com.example.data.data_source.remote.models

data class ArticleDataModel(
    var source      : SourceDataModel? = null,
    var author      : String? = null,
    var title       : String? = null,
    var description : String? = null,
    var url         : String? = null,
    var urlToImage  : String? = null,
    var publishedAt : String? = null,
    var content     : String? = null
)
