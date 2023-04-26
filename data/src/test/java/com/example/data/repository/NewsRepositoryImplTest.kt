package com.example.data.repository

import android.net.ConnectivityManager
import com.example.data.data_source.remote.api.NewsApi
import com.example.data.data_source.remote.models.NewsResponseDataModel
import com.example.data.utils.isConnectedToInternet
import com.example.domain.models.ArticleDomainModel
import com.example.domain.models.CustomExceptionDomainModel
import com.example.domain.models.NewsQueryDomainModel
import com.example.domain.repository.NewsRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.io.InterruptedIOException
import java.net.HttpURLConnection

class NewsRepositoryImplTest {

    private val api: NewsApi = mockk()
    /* relaxed = true -> provides default stubs for all methods */
    private val connectivityManager: ConnectivityManager = mockk(relaxed = true)
    private val repo: NewsRepository = NewsRepositoryImpl(api = api, connectivityManager = connectivityManager)
    val query = NewsQueryDomainModel("technology")

    @Test
    fun `getNews() with network connectivity then return domain articles`() {
        runBlocking {
            // Arrange
            every { connectivityManager.isConnectedToInternet() } returns (true)
            coEvery { api.getNews(searchStatement = query.searchStatement) } returns (
                    NewsResponseDataModel(articles = fakeDataModelArticles)
                    )

            // Act
            val result = repo.getNews(query)

            // Assert
//            verify(exactly = 1) { any<ArticleDataModel>().toArticleDomainModel() }
            val expectedArticles = fakeDomainModelArticles
            assertEquals(expectedArticles, result)
        }
    }


    @Test
    fun `getNews() with network connectivity and articles equal null then return empty list`() {
        runBlocking {
            // Arrange
            every { connectivityManager.isConnectedToInternet() } returns (true)
            coEvery { api.getNews(searchStatement = query.searchStatement) } returns (
                    NewsResponseDataModel(articles = null)
                    )

            // Act
            val result = repo.getNews(query)

            // Assert
            assertEquals(emptyList<ArticleDomainModel>(), result)
        }
    }


    @Test(expected = CustomExceptionDomainModel.NoInternetConnectionExceptionDomainModel::class)
    fun `getNews() without network connectivity, then throw NoInternetConnectionExceptionDomainModel`() {
        runBlocking {
            // Arrange
            coEvery { api.getNews(searchStatement = query.searchStatement) } returns (
                    NewsResponseDataModel(articles = fakeDataModelArticles)
                    )

            // Act
            repo.getNews(query)
        }
    }

    @Test(expected = CustomExceptionDomainModel.TimeoutExceptionDomainModel::class)
    fun `getNews() with network connectivity and call timeout error, then throw TimeoutExceptionDomainModel`() {
        runBlocking {
            // Arrange
            every { connectivityManager.isConnectedToInternet() } returns (true)
            coEvery { api.getNews(searchStatement = query.searchStatement) } throws (InterruptedIOException())

            // Act
            repo.getNews(query)
        }
    }

    @Test(expected = CustomExceptionDomainModel.NetworkExceptionDomainModel::class)
    fun `getNews() with network connectivity and IOException, then throw NetworkExceptionDomainModel`() {
        runBlocking {
            // Arrange
            every { connectivityManager.isConnectedToInternet() } returns (true)
            coEvery { api.getNews(searchStatement = query.searchStatement) } throws (IOException())

            // Act
            repo.getNews(query)
        }
    }

    @Test(expected = CustomExceptionDomainModel.ServiceNotFoundExceptionDomainModel::class)
    fun `getNews() with network connectivity and HttpException with NotFound http code, then throw ServiceNotFoundExceptionDomainModel`() {
        runBlocking {
            // Arrange
            every { connectivityManager.isConnectedToInternet() } returns (true)
            coEvery { api.getNews(searchStatement = query.searchStatement) } throws (
                    HttpException(Response.error<Any>(HttpURLConnection.HTTP_NOT_FOUND, "".toResponseBody()))
            )

            // Act
            repo.getNews(query)
        }
    }

    @Test(expected = CustomExceptionDomainModel.AccessDeniedExceptionDomainModel::class)
    fun `getNews() with network connectivity and HttpException with Forbidden http code, then throw AccessDeniedExceptionDomainModel`() {
        runBlocking {
            // Arrange
            every { connectivityManager.isConnectedToInternet() } returns (true)
            coEvery { api.getNews(searchStatement = query.searchStatement) } throws (
                    HttpException(Response.error<Any>(HttpURLConnection.HTTP_FORBIDDEN, "".toResponseBody()))
                    )

            // Act
            repo.getNews(query)
        }
    }

    @Test(expected = CustomExceptionDomainModel.ServiceUnavailableExceptionDomainModel::class)
    fun `getNews() with network connectivity and HttpException with Forbidden http code, then throw ServiceUnavailableExceptionDomainModel`() {
        runBlocking {
            // Arrange
            every { connectivityManager.isConnectedToInternet() } returns (true)
            coEvery { api.getNews(searchStatement = query.searchStatement) } throws (
                    HttpException(Response.error<Any>(HttpURLConnection.HTTP_UNAVAILABLE, "".toResponseBody()))
                    )

            // Act
            repo.getNews(query)
        }
    }

    @Test(expected = CustomExceptionDomainModel.UnknownExceptionDomainModel::class)
    fun `getNews() with network connectivity and unknown exception, then throw UnknownExceptionDomainModel`() {
        runBlocking {
            // Arrange
            every { connectivityManager.isConnectedToInternet() } returns (true)
            coEvery { api.getNews(searchStatement = query.searchStatement) } throws (Exception())

            // Act
            repo.getNews(query)
        }
    }

}