package com.example.composenewsapp.domain.model

data class Article(
    var title       : String,
    var description : String,
    var url         : String,
    var urlToImage  : String,
    var publishedAt : String,
    var author      : String,
)
