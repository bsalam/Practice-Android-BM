package com.example.presentation.mapper

import com.example.domain.models.CustomExceptionDomainModel
import com.example.presentation.models.CustomExceptionPresentationModel
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ToCustomExceptionPresentationModelMapperTest(private val input: CustomExceptionDomainModel, private val expected: CustomExceptionPresentationModel) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: input = {0}, expected = {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(CustomExceptionDomainModel.NoInternetConnectionExceptionDomainModel, CustomExceptionPresentationModel.NoInternetConnection),
                arrayOf(CustomExceptionDomainModel.TimeoutExceptionDomainModel, CustomExceptionPresentationModel.Timeout),
                arrayOf(CustomExceptionDomainModel.NetworkExceptionDomainModel, CustomExceptionPresentationModel.Network),
                arrayOf(CustomExceptionDomainModel.ServiceNotFoundExceptionDomainModel, CustomExceptionPresentationModel.ServiceUnreachable),
                arrayOf(CustomExceptionDomainModel.AccessDeniedExceptionDomainModel, CustomExceptionPresentationModel.ServiceUnreachable),
                arrayOf(CustomExceptionDomainModel.ServiceUnavailableExceptionDomainModel, CustomExceptionPresentationModel.ServiceUnreachable),
                arrayOf(CustomExceptionDomainModel.UnknownExceptionDomainModel, CustomExceptionPresentationModel.Unknown)
            )
        }
    }

    @Test
    fun `toCustomExceptionPresentationModel(), then should map CustomExceptionDomainModel to CustomExceptionPresentationModel`() {
        // Act
        val actual = input.toCustomExceptionPresentationModel()

        // Assert
        assertEquals(expected, actual)
    }
}