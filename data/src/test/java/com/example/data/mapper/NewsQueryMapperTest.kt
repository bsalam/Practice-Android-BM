package com.example.data.mapper

import com.example.data.data_source.remote.models.NewsQueryDataModel
import com.example.domain.models.NewsQueryDomainModel
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class NewsQueryMapperTest(
    private val inputDomainModel: NewsQueryDomainModel,
    private val expectedDataModel: NewsQueryDataModel
){

    @Test
    fun `toNewsQueryDataModel() with NewsQueryDomain model as an input, then return NewsQueryDataModel`() {
        // Act
        val result = inputDomainModel.toNewsQueryDataModel()

        // Assert
        assertEquals(expectedDataModel, result)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: input = {0}, expected = {1}")
        fun data() = listOf(
            arrayOf(
                NewsQueryDomainModel(
                    searchStatement = "Test Search Statement",
                    pageNumber = 1,
                    fromDate = "2011-01-01",
                    toDate = "2021-01-01",
                    language = "en"
                ),
                NewsQueryDataModel(
                    searchStatement = "Test Search Statement",
                    pageNumber = 1,
                    fromDate = "2011-01-01",
                    toDate = "2021-01-01",
                    language = "en"
                )
            ),
            arrayOf(
                NewsQueryDomainModel(
                    searchStatement = "Test Search Statement 2",
                    pageNumber = 2,
                    fromDate = "2012-02-02",
                    toDate = "2022-02-02",
                    language = "en"
                ),
                NewsQueryDataModel(
                    searchStatement = "Test Search Statement 2",
                    pageNumber = 2,
                    fromDate = "2012-02-02",
                    toDate = "2022-02-02",
                    language = "en"
                )
            )
        )
    }

}