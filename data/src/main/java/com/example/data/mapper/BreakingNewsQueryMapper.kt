package com.example.data.mapper

import com.example.data.data_source.remote.models.BreakingNewsQueryDataModel
import com.example.domain.models.BreakingNewsQueryDomainModel

fun BreakingNewsQueryDomainModel.toBreakingNewsQueryDataModel(): BreakingNewsQueryDataModel {
    return BreakingNewsQueryDataModel(
        searchStatement = searchStatement,
        pageNumber = pageNumber,
        countryCode = countryCode
    )
}