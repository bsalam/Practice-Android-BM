package com.example.presentation.base

import app.cash.turbine.test
import com.example.domain.models.CustomExceptionDomainModel
import com.example.domain.models.NewsQueryDomainModel
import com.example.domain.use_cases.FetchNewsUseCase
import com.example.presentation.utils.MainCoroutineRule
import com.example.presentation.utils.TestDispatchers
import com.example.presentation.models.CustomExceptionPresentationModel
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BaseViewModelTest {

    private lateinit var classUnderTest: BaseViewModel
    private lateinit var fetchNewsUseCase: FetchNewsUseCase
    private lateinit var testDispatchers: TestDispatchers

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        testDispatchers = TestDispatchers()
        fetchNewsUseCase = mockk(relaxed = true)
        classUnderTest = BaseViewModel(testDispatchers)
    }

    @Test
    fun `setState(), when state is loading, then it should update state to Loading State`() =
        runTest {
            // given
            val expectedBaseState = BaseState.Loading

            // when
            classUnderTest.setState(expectedBaseState)

            // then
            classUnderTest.baseState.test {
                val actualBaseState = awaitItem()
                assertEquals(expectedBaseState, actualBaseState)
                cancelAndConsumeRemainingEvents() // To make sure that we finish this test scope
            } // if there are more events coming in after the last one here then it will throw an exception
        }

    @Test
    fun `setState(), when state is error, then it should update state to Loading State`() =
        runTest {
            // given
            val expectedBaseState =
                BaseState.Error(CustomExceptionPresentationModel.NoInternetConnection)

            // when
            classUnderTest.setState(expectedBaseState)

            // then
            classUnderTest.baseState.test {
                assertEquals(expectedBaseState, awaitItem())
                cancelAndConsumeRemainingEvents() // To make sure that we finish this test scope
            } // if there are more events coming in after the last one here then it will throw an exception
        }


    @Test
    fun `executeUseCase(), when UnknownExceptionDomainModel is thrown then baseState should be equal BaseState_Error(CustomExceptionPresentationModel_Unknown)`() =
        runTest {
            // Given
            val newsQueryDomainModel = NewsQueryDomainModel("technology")
            val expectedCustomException = CustomExceptionDomainModel.UnknownExceptionDomainModel
            val actualPresentationModel = CustomExceptionPresentationModel.Unknown
            coEvery { fetchNewsUseCase.invoke(newsQueryDomainModel) } throws expectedCustomException

            // when
            classUnderTest.executeUseCase { fetchNewsUseCase.invoke(newsQueryDomainModel) }
            delay(10L)

            // then
            classUnderTest.baseState.test {
                assertEquals(BaseState.Error(actualPresentationModel), awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }
}


/** Another Way
@Test
fun `setState(), when state is loading, then it should update state to Loading State`()  {
// given
val expectedBaseState = BaseState.Loading

// when
baseViewModel.setState(expectedBaseState)

// then
assertEquals(expectedBaseState, baseViewModel.baseState.value)

}
 **/

/**
 *      @get:Rule                                      =====>   Dispatchers.setMain(testDispatcher.main)
 *      var mainCoroutineRule = MainCoroutineRule()    =====>   Dispatchers.resetMain() "in tearDown (@After)"
 *
 **/