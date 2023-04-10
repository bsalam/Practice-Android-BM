package com.example.domain.use_cases.breaking_news

import com.example.domain.models.BreakingNewsQueryDomainModel
import com.example.domain.models.CustomExceptionDomainModel
import com.example.domain.repository.NewsRepository
import com.example.domain.use_cases.FetchBreakingNewsUseCase
import com.example.domain.use_cases.fakeArticles
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import kotlin.test.assertFailsWith

class FetchBreakingNewsUseCaseTest {
    private val newsRepository: NewsRepository = mockk()
    private val fetchBreakingNewsUseCase = FetchBreakingNewsUseCase(newsRepository)

    @Test
    fun `when invoke is called with news query domain model, then it should return a list of article domain models`() =
        runBlocking {
            // Arrange
            val breakingNewsQueryDomainModel = BreakingNewsQueryDomainModel("technology")
            val expectedArticles = fakeArticles
            coEvery { newsRepository.getBreakingNews(breakingNewsQueryDomainModel) } returns expectedArticles

            // Act
            val result = fetchBreakingNewsUseCase(breakingNewsQueryDomainModel)

            // Assert
            assertEquals(expectedArticles, result)
            coVerify(exactly = 1) { newsRepository.getBreakingNews(breakingNewsQueryDomainModel) }
        }


    @Test
    fun `when repository throws a NetworkExceptionDomainModel, then invoke should throw NetworkExceptionDomainModel`() =
        runBlocking {
            // Arrange
            val breakingNewsQueryDomainModel = BreakingNewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.NetworkExceptionDomainModel
            coEvery { newsRepository.getBreakingNews(breakingNewsQueryDomainModel) } throws expectedException

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.NetworkExceptionDomainModel> {
                fetchBreakingNewsUseCase(breakingNewsQueryDomainModel)
            }
            coVerify(exactly = 1) { newsRepository.getBreakingNews(breakingNewsQueryDomainModel) }
        }

    @Test
    fun `when repository throws a NoInternetConnectionExceptionDomainModel, then invoke should throw NoInternetConnectionExceptionDomainModel`() =
        runBlocking {
            // Arrange
            val breakingNewsQueryDomainModel = BreakingNewsQueryDomainModel("technology")
            val expectedException =
                CustomExceptionDomainModel.NoInternetConnectionExceptionDomainModel
            coEvery { newsRepository.getBreakingNews(breakingNewsQueryDomainModel) } throws expectedException

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.NoInternetConnectionExceptionDomainModel> {
                fetchBreakingNewsUseCase(breakingNewsQueryDomainModel)
            }
            coVerify(exactly = 1) { newsRepository.getBreakingNews(breakingNewsQueryDomainModel) }
        }

    @Test
    fun `when repository throws a TimeoutExceptionDomainModel, then invoke should throw TimeoutExceptionDomainModel`() =
        runBlocking {
            // Arrange
            val breakingNewsQueryDomainModel = BreakingNewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.TimeoutExceptionDomainModel
            coEvery { newsRepository.getBreakingNews(breakingNewsQueryDomainModel) } throws expectedException

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.TimeoutExceptionDomainModel> {
                fetchBreakingNewsUseCase(breakingNewsQueryDomainModel)
            }
            coVerify(exactly = 1) { newsRepository.getBreakingNews(breakingNewsQueryDomainModel) }
        }

    @Test
    fun `when repository throws a ServiceNotFoundExceptionDomainModel, then invoke should throw ServiceNotFoundExceptionDomainModel`() =
        runBlocking {
            // Arrange
            val breakingNewsQueryDomainModel = BreakingNewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.ServiceNotFoundExceptionDomainModel
            coEvery { newsRepository.getBreakingNews(breakingNewsQueryDomainModel) } throws expectedException

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.ServiceNotFoundExceptionDomainModel> {
                fetchBreakingNewsUseCase(breakingNewsQueryDomainModel)
            }
            coVerify(exactly = 1) { newsRepository.getBreakingNews(breakingNewsQueryDomainModel) }
        }

    @Test
    fun `when repository throws a AccessDeniedExceptionDomainModel, then invoke should throw AccessDeniedExceptionDomainModel`() =
        runBlocking {
            // Arrange
            val breakingNewsQueryDomainModel = BreakingNewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.AccessDeniedExceptionDomainModel
            coEvery { newsRepository.getBreakingNews(breakingNewsQueryDomainModel) } throws expectedException

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.AccessDeniedExceptionDomainModel> {
                fetchBreakingNewsUseCase(breakingNewsQueryDomainModel)
            }
            coVerify(exactly = 1) { newsRepository.getBreakingNews(breakingNewsQueryDomainModel) }
        }

    @Test
    fun `when repository throws a ServiceUnavailableExceptionDomainModel, then invoke should throw ServiceUnavailableExceptionDomainModel`() =
        runBlocking {
            // Arrange
            val breakingNewsQueryDomainModel = BreakingNewsQueryDomainModel("technology")
            val expectedException =
                CustomExceptionDomainModel.ServiceUnavailableExceptionDomainModel
            coEvery { newsRepository.getBreakingNews(breakingNewsQueryDomainModel) } throws expectedException

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.ServiceUnavailableExceptionDomainModel> {
                fetchBreakingNewsUseCase(breakingNewsQueryDomainModel)
            }
            coVerify(exactly = 1) { newsRepository.getBreakingNews(breakingNewsQueryDomainModel) }
        }

    @Test
    fun `when repository throws a UnknownExceptionDomainModel, then invoke should throw UnknownExceptionDomainModel`() =
        runBlocking {
            // Arrange
            val breakingNewsQueryDomainModel = BreakingNewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.UnknownExceptionDomainModel
            coEvery { newsRepository.getBreakingNews(breakingNewsQueryDomainModel) } throws expectedException

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.UnknownExceptionDomainModel> {
                fetchBreakingNewsUseCase(breakingNewsQueryDomainModel)
            }
            coVerify(exactly = 1) { newsRepository.getBreakingNews(breakingNewsQueryDomainModel) }
        }

    // coVerify{}: Verifies that calls were made inside a coroutine.
    // coEvery{}: Starts a block of stubbing for coroutines. Part of DSL. Similar to every,
    // but works with suspend functions.Used to define what behaviour is going to be mocked.
    // assertFailsWith<Exception> {}: Asserts that a block fails with a specific exception of type T being thrown.
}