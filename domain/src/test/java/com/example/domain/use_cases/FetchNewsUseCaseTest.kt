package com.example.domain.use_cases

import com.example.domain.models.CustomExceptionDomainModel
import com.example.domain.models.NewsQueryDomainModel
import com.example.domain.repository.NewsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import kotlin.test.assertFailsWith

class FetchNewsUseCaseTest {
    private val newsRepository: NewsRepository = mockk()
    private val fetchNewsUseCase = FetchNewsUseCase(newsRepository)

    @Test
    fun `when invoke is called with news query domain model, then it should return a list of article domain models`() =
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedArticles = fakeArticles
            coEvery { newsRepository.getNews(newsQueryDomainModel) } returns expectedArticles

            // Act
            val result = fetchNewsUseCase(newsQueryDomainModel)

            // Assert
            assertEquals(expectedArticles, result)
            coVerify(exactly = 1) { newsRepository.getNews(newsQueryDomainModel) }
        }


    @Test
    fun `when repository throws a NetworkExceptionDomainModel, then invoke should throw the same exception`() =
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.NetworkExceptionDomainModel
            coEvery { newsRepository.getNews(newsQueryDomainModel) } throws expectedException

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.NetworkExceptionDomainModel> {
                fetchNewsUseCase(newsQueryDomainModel)
            }
            coVerify(exactly = 1) { newsRepository.getNews(newsQueryDomainModel) }
        }

    @Test
    fun `when repository throws a NoInternetConnectionExceptionDomainModel, then invoke should throw the same exception`() =
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedException =
                CustomExceptionDomainModel.NoInternetConnectionExceptionDomainModel
            coEvery { newsRepository.getNews(newsQueryDomainModel) } throws expectedException

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.NoInternetConnectionExceptionDomainModel> {
                fetchNewsUseCase(newsQueryDomainModel)
            }
            coVerify(exactly = 1) { newsRepository.getNews(newsQueryDomainModel) }
        }

    @Test
    fun `when repository throws a TimeoutExceptionDomainModel, then invoke should throw the same exception`() =
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.TimeoutExceptionDomainModel
            coEvery { newsRepository.getNews(newsQueryDomainModel) } throws expectedException

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.TimeoutExceptionDomainModel> {
                fetchNewsUseCase(newsQueryDomainModel)
            }
            coVerify(exactly = 1) { newsRepository.getNews(newsQueryDomainModel) }
        }

    @Test
    fun `when repository throws a ServiceNotFoundExceptionDomainModel, then invoke should throw the same exception`() =
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.ServiceNotFoundExceptionDomainModel
            coEvery { newsRepository.getNews(newsQueryDomainModel) } throws expectedException

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.ServiceNotFoundExceptionDomainModel> {
                fetchNewsUseCase(newsQueryDomainModel)
            }
            coVerify(exactly = 1) { newsRepository.getNews(newsQueryDomainModel) }
        }

    @Test
    fun `when repository throws a AccessDeniedExceptionDomainModel, then invoke should throw the same exception`() =
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.AccessDeniedExceptionDomainModel
            coEvery { newsRepository.getNews(newsQueryDomainModel) } throws expectedException

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.AccessDeniedExceptionDomainModel> {
                fetchNewsUseCase(newsQueryDomainModel)
            }
            coVerify(exactly = 1) { newsRepository.getNews(newsQueryDomainModel) }
        }

    @Test
    fun `when repository throws a ServiceUnavailableExceptionDomainModel, then invoke should throw the same exception`() =
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedException =
                CustomExceptionDomainModel.ServiceUnavailableExceptionDomainModel
            coEvery { newsRepository.getNews(newsQueryDomainModel) } throws expectedException

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.ServiceUnavailableExceptionDomainModel> {
                fetchNewsUseCase(newsQueryDomainModel)
            }
            coVerify(exactly = 1) { newsRepository.getNews(newsQueryDomainModel) }
        }

    @Test
    fun `when repository throws a UnknownExceptionDomainModel, then invoke should throw the same exception`() =
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.UnknownExceptionDomainModel
            coEvery { newsRepository.getNews(newsQueryDomainModel) } throws expectedException

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.UnknownExceptionDomainModel> {
                fetchNewsUseCase(newsQueryDomainModel)
            }
            coVerify(exactly = 1) { newsRepository.getNews(newsQueryDomainModel) }
        }

    // coVerify{}: Verifies that calls were made inside a coroutine.
    // coEvery{}: Starts a block of stubbing for coroutines. Part of DSL. Similar to every,
    // but works with suspend functions.Used to define what behaviour is going to be mocked.
    // assertFailsWith<Exception> {}: Asserts that a block fails with a specific exception of type T being thrown.
}