package com.example.data.mapper

import com.example.data.data_source.remote.models.NewsQueryDataModel
import com.example.domain.models.NewsQueryDomainModel

fun NewsQueryDomainModel.toNewsQueryDataModel(): NewsQueryDataModel {
    return NewsQueryDataModel(
        searchStatement = searchStatement,
        pageNumber = pageNumber,
        fromDate = fromDate,
        toDate = toDate,
        language = language
    )
}