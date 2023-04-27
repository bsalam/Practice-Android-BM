package com.example.data

import com.example.data.data_source.remote.models.ArticleDataModel
import com.example.data.data_source.remote.models.SourceDataModel

val fakeDataModelArticles = listOf(
    ArticleDataModel(
        SourceDataModel("", ""),
        "authorName",
        "Title",
        "Description",
        "url",
        "imageUrl",
        "date",
        ""
    ),
    ArticleDataModel(
        SourceDataModel("", ""),
        "authorName2",
        "Title2",
        "Description2",
        "url2",
        "imageUrl2",
        "date2",
        ""
    )
)