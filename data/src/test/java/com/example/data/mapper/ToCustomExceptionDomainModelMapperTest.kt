package com.example.data.mapper

import com.example.domain.models.CustomExceptionDomainModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.Assert.assertEquals
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.io.InterruptedIOException

@RunWith(Parameterized::class) //  is used to indicate that the test class will use parameterized tests.
class ToCustomExceptionDomainModelMapperTest(private val throwable: Throwable, private val expected: CustomExceptionDomainModel) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: throwable = {0}, Expected = {1}") //  indicates that this method provides the data for the parameterized tests.
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(IOException(), CustomExceptionDomainModel.NetworkExceptionDomainModel),
                arrayOf(InterruptedIOException(), CustomExceptionDomainModel.TimeoutExceptionDomainModel),
                arrayOf(HttpException(Response.error<Any>(404, "".toResponseBody())), CustomExceptionDomainModel.ServiceNotFoundExceptionDomainModel),
                arrayOf(HttpException(Response.error<Any>(403, "".toResponseBody())), CustomExceptionDomainModel.AccessDeniedExceptionDomainModel),
                arrayOf(HttpException(Response.error<Any>(503, "".toResponseBody())), CustomExceptionDomainModel.ServiceUnavailableExceptionDomainModel),
                arrayOf(HttpException(Response.error<Any>(500, "".toResponseBody())), CustomExceptionDomainModel.UnknownExceptionDomainModel),
                arrayOf(IllegalArgumentException(), CustomExceptionDomainModel.UnknownExceptionDomainModel)
            )
        }
    }

    @Test
    fun `should map throwable to custom exception domain model`() {
        // Act
        val actual = throwable.toCustomExceptionDomainModel()

        // Assert
        assertEquals(expected, actual)
    }
}