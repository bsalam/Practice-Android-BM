package com.example.presentation.screen.news

import app.cash.turbine.test
import com.example.domain.models.ArticleDomainModel
import com.example.domain.models.CustomExceptionDomainModel
import com.example.domain.models.NewsQueryDomainModel
import com.example.domain.use_cases.FetchNewsUseCase
import com.example.presentation.utils.MainCoroutineRule
import com.example.presentation.utils.TestDispatchers
import com.example.presentation.base.BaseState
import com.example.presentation.models.CustomExceptionPresentationModel
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsViewModelTest {

    private lateinit var classUnderTest: NewsViewModel
    private lateinit var fetchNewsUseCase: FetchNewsUseCase
    private lateinit var testDispatcher: TestDispatchers

    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    @Before
    fun setup() {
        testDispatcher = TestDispatchers()
        fetchNewsUseCase = mockk(relaxed = true)
        classUnderTest = NewsViewModel(fetchNewsUseCase, testDispatcher)
    }

    @Test
    fun `requestNews(), when successful call, then should emit newsState with list of articles`() =
        runTest {
            // Given
            val query = NewsQueryDomainModel("test")
            val articles = listOf(ArticleDomainModel("", "", "", "", "", ""))
            coEvery { fetchNewsUseCase.invoke(query) } returns articles

            // When
            classUnderTest.requestNews(query)
            delay(10L)

            // Then
            classUnderTest.baseState.test {
                assertEquals(BaseState.Loading, awaitItem())
                assertEquals(BaseState.Empty, awaitItem())
                cancelAndConsumeRemainingEvents() // Cancel collecting events from the source Flow. Any events which have already been received will be returned.
            }
            classUnderTest.newsState.test {
                assertEquals(articles, awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `requestNews with exception should emit error state`() = runTest {
        // Given
        val query = NewsQueryDomainModel("test")
        coEvery { fetchNewsUseCase.invoke(query) } throws CustomExceptionDomainModel.NoInternetConnectionExceptionDomainModel

        // When
        classUnderTest.requestNews(query)

        // Then
        classUnderTest.baseState.test {
            assertEquals(BaseState.Loading, awaitItem())
            assertEquals(
                BaseState.Error(CustomExceptionPresentationModel.NoInternetConnection),
                awaitItem()
            )
            cancelAndConsumeRemainingEvents()
        }
    }
}