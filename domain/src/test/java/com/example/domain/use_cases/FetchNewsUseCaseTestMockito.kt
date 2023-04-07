package com.example.domain.use_cases

import com.example.domain.models.CustomExceptionDomainModel
import com.example.domain.models.NewsQueryDomainModel
import com.example.domain.repository.NewsRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertFailsWith

@RunWith(MockitoJUnitRunner::class)
class FetchNewsUseCaseTestMockito {

    @Mock
    private lateinit var newsRepository: NewsRepository

    private lateinit var fetchNewsUseCase: FetchNewsUseCase

    @Before
    fun setUp() {
        fetchNewsUseCase = FetchNewsUseCase(newsRepository)
    }

    @Test
    fun `when invoke is called with news query domain model, then it should return a list of article domain models`(): Unit =
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedArticles = fakeArticles
            whenever(newsRepository.getNews(newsQueryDomainModel)).thenReturn(expectedArticles)

            // Act
            val result = fetchNewsUseCase(newsQueryDomainModel)

            // Assert
            assertEquals(expectedArticles, result)
            verify(newsRepository).getNews(newsQueryDomainModel)
        }

    @Test
    fun `when repository throws a NetworkExceptionDomainModel, then invoke should throw the same exception`() {
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.NetworkExceptionDomainModel
            whenever(newsRepository.getNews(newsQueryDomainModel)).doAnswer { throw expectedException }

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.NetworkExceptionDomainModel> {
                fetchNewsUseCase(newsQueryDomainModel)
            }

            // Assert
            verify(newsRepository, times(1)).getNews(newsQueryDomainModel)
        }
    }
    @Test
    fun `when repository throws a NoInternetConnectionExceptionDomainModel, then invoke should throw the same exception`() {
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.NoInternetConnectionExceptionDomainModel
            whenever(newsRepository.getNews(newsQueryDomainModel)).doAnswer { throw expectedException }

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.NoInternetConnectionExceptionDomainModel> {
                fetchNewsUseCase(newsQueryDomainModel)
            }

            // Assert
            verify(newsRepository, times(1)).getNews(newsQueryDomainModel)
        }
    }

    @Test
    fun `when repository throws a TimeoutExceptionDomainModel, then invoke should throw the same exception`() {
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.TimeoutExceptionDomainModel
            whenever(newsRepository.getNews(newsQueryDomainModel)).doAnswer { throw expectedException }

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.TimeoutExceptionDomainModel> {
                fetchNewsUseCase(newsQueryDomainModel)
            }

            // Assert
            verify(newsRepository, times(1)).getNews(newsQueryDomainModel)
        }
    }

    @Test
    fun `when repository throws a ServiceNotFoundExceptionDomainModel, then invoke should throw the same exception`() {
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.ServiceNotFoundExceptionDomainModel
            whenever(newsRepository.getNews(newsQueryDomainModel)).doAnswer { throw expectedException }

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.ServiceNotFoundExceptionDomainModel> {
                fetchNewsUseCase(newsQueryDomainModel)
            }

            // Assert
            verify(newsRepository, times(1)).getNews(newsQueryDomainModel)
        }
    }
    @Test
    fun `when repository throws a AccessDeniedExceptionDomainModel, then invoke should throw the same exception`() {
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.AccessDeniedExceptionDomainModel
            whenever(newsRepository.getNews(newsQueryDomainModel)).doAnswer { throw expectedException }

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.AccessDeniedExceptionDomainModel> {
                fetchNewsUseCase(newsQueryDomainModel)
            }

            // Assert
            verify(newsRepository, times(1)).getNews(newsQueryDomainModel)
        }
    }
    @Test
    fun `when repository throws a ServiceUnavailableExceptionDomainModel, then invoke should throw the same exception`() {
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.ServiceUnavailableExceptionDomainModel
            whenever(newsRepository.getNews(newsQueryDomainModel)).doAnswer { throw expectedException }

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.ServiceUnavailableExceptionDomainModel> {
                fetchNewsUseCase(newsQueryDomainModel)
            }

            // Assert
            verify(newsRepository, times(1)).getNews(newsQueryDomainModel)
        }
    }
    @Test
    fun `when repository throws a UnknownExceptionDomainModel, then invoke should throw the same exception`() {
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedException = CustomExceptionDomainModel.UnknownExceptionDomainModel
            whenever(newsRepository.getNews(newsQueryDomainModel)).doAnswer { throw expectedException }

            // Act & Assert
            assertFailsWith<CustomExceptionDomainModel.UnknownExceptionDomainModel> {
                fetchNewsUseCase(newsQueryDomainModel)
            }

            // Assert
            verify(newsRepository, times(1)).getNews(newsQueryDomainModel)
        }
    }
}

// verify(): Verifies certain behavior happened at least once / exact number of times / never. ( default = 1 )
// doAnswer(): Sets a generic Answer for the method using a lambda.

/**
 * In Mockito, thenReturn is used to return a value for a method call, while doReturn is used to specify a return value for a method call within
 * a when block. The difference is that thenReturn works with non-void methods and has a simpler syntax, while doReturn works with both void
 * and non-void methods but requires the use of the when block.

doAnswer is similar to doReturn, but it allows you to specify custom behavior for a method call, such as throwing an exception or performing
some other action. doAnswer takes a Answer object as a parameter, which is responsible for executing the custom behavior.
 **/