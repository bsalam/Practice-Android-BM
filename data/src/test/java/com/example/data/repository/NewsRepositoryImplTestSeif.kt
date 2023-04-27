package com.example.data.repository

import android.net.ConnectivityManager
import com.example.data.data_source.remote.api.NewsApi
import com.example.data.fakeNewsResponseDataModel
import com.example.data.utils.isConnectedToInternet
import com.example.domain.models.CustomExceptionDomainModel
import com.example.domain.models.NewsQueryDomainModel
import com.example.domain.repository.NewsRepository
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.io.InterruptedIOException

class NewsRepositoryImplTestSeif {

    private lateinit var api: NewsApi
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var newsRepository: NewsRepository

    @Before
    fun setUp() {
        api = mockk()
        connectivityManager = mockk(relaxed = true) // allows you to create a mock object that ignores any method calls that are not explicitly stubbed
        newsRepository = NewsRepositoryImpl(api, connectivityManager)
    }

    @Test
    fun `getNews(), when there is internet connection then we should get news successfully`() =
        runBlocking {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("bitcoin")
            val fakeNewsResponseDataModel = fakeNewsResponseDataModel
            val expected = com.example.data.fakeDomainModelArticles

            every { connectivityManager.isConnectedToInternet() } returns true
            coEvery {
                api.getNews(any())
            } returns fakeNewsResponseDataModel

            // Act
            val actual = newsRepository.getNews(newsQueryDomainModel)

            // Assert
            assertEquals(expected, actual)
        }

    @Test
    fun `getNews(), when no internet connection is available, then should throw NoInternetConnectionExceptionDomainModel`() {
        // Arrange
        val newsQueryDomainModel = NewsQueryDomainModel("bitcoin")
        every { connectivityManager.isConnectedToInternet() } returns false

        // Act and Assert
        assertThrows(CustomExceptionDomainModel.NoInternetConnectionExceptionDomainModel::class.java) {
            runBlocking { newsRepository.getNews(newsQueryDomainModel) }
        }
        coVerify(inverse = true) { api.getNews(any()) } // verify that it's not called

    }

    @Test
    fun `getNews(),when getNews call fails due to network issue, then it should throw NetworkExceptionDomainModel `() {
        // Arrange
        val newsQueryDomainModel = NewsQueryDomainModel("bitcoin")
        every { connectivityManager.isConnectedToInternet() } returns true
        coEvery { api.getNews(any()) } throws IOException()

        // Act and Assert
        assertThrows(CustomExceptionDomainModel.NetworkExceptionDomainModel::class.java) {
            runBlocking { newsRepository.getNews(newsQueryDomainModel) }
        }
    }

    @Test
    fun `getNews(),when getNews call fails due to service not found issue, then it should throw ServiceNotFoundExceptionDomainModel `() {
        // Arrange
        val newsQueryDomainModel = NewsQueryDomainModel("bitcoin")
        val expectedException = HttpException(Response.error<Any>(404, "".toResponseBody()))
        every { connectivityManager.isConnectedToInternet() } returns true
        coEvery { api.getNews(any()) } throws expectedException

        // Act and Assert
        assertThrows(CustomExceptionDomainModel.ServiceNotFoundExceptionDomainModel::class.java) {
            runBlocking { newsRepository.getNews(newsQueryDomainModel) }
        }
    }

    @Test
    fun `getNews(),when getNews call fails due to access Denied issue, then it should throw AccessDeniedExceptionDomainModel `() {
        // Arrange
        val newsQueryDomainModel = NewsQueryDomainModel("bitcoin")
        val expectedException = HttpException(Response.error<Any>(403, "".toResponseBody()))
        every { connectivityManager.isConnectedToInternet() } returns true
        coEvery { api.getNews(any()) } throws expectedException

        // Act and Assert
        assertThrows(CustomExceptionDomainModel.AccessDeniedExceptionDomainModel::class.java) {
            runBlocking { newsRepository.getNews(newsQueryDomainModel) }
        }
    }

    @Test
    fun `getNews(),when getNews call fails due to service unavailable issue, then it should throw ServiceUnavailableExceptionDomainModel `() {
        // Arrange
        val newsQueryDomainModel = NewsQueryDomainModel("bitcoin")
        val expectedException = HttpException(Response.error<Any>(503, "".toResponseBody()))
        every { connectivityManager.isConnectedToInternet() } returns true
        coEvery { api.getNews(any()) } throws expectedException

        // Act and Assert
        assertThrows(CustomExceptionDomainModel.ServiceUnavailableExceptionDomainModel::class.java) {
            runBlocking { newsRepository.getNews(newsQueryDomainModel) }
        }
    }

    @Test
    fun `getNews(), when getNews call takes longer than expected time, then it should throw TimeoutExceptionDomainModel `() {
        // Arrange
        val newsQueryDomainModel = NewsQueryDomainModel("bitcoin")
        every { connectivityManager.isConnectedToInternet() } returns true
        coEvery { api.getNews(any()) } throws InterruptedIOException()
        // Act and Assert
        assertThrows(CustomExceptionDomainModel.TimeoutExceptionDomainModel::class.java) {
            runBlocking { newsRepository.getNews(newsQueryDomainModel) }
        }
    }

    @Test
    fun `getNews(), when getNews call fails with an unexpected exception, then it should throw UnknownExceptionDomainModel `() {
            // Arrange
            val newsQueryDomainModel = NewsQueryDomainModel("bitcoin")
            every { connectivityManager.isConnectedToInternet() } returns true
            coEvery { api.getNews(any()) } throws RuntimeException()

            // Act and Assert
            assertThrows(CustomExceptionDomainModel.UnknownExceptionDomainModel::class.java) {
                runBlocking { newsRepository.getNews(newsQueryDomainModel) }
            }
        }
}
/**
 *  verify is used for synchronous functions, while coVerify is used for suspend functions.
 *
 *  assertFailsWith is used to test the message or the cause of the thrown exception.
 *  It takes a lambda or function as a parameter, and returns the thrown exception,
 *  which you can then test using additional assertions.
 *
 *  assertThrows is used to test if a specific exception is thrown by a block of code.
 *  It takes a lambda or function as a parameter, along with the class of the expected exception.
 *  If the block of code throws an exception of the expected class, the assertion passes.
 *  Otherwise, the assertion fails.
 *
 *  every and coEvery are used to stub/mock a method call, but every is used for regular methods that
 *  return immediately and coEvery is used for suspend functions that might have a delay or run on a different thread.
 **/
