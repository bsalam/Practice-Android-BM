package com.example.composenewsapp.presentation.base

import androidx.lifecycle.ViewModel
import com.example.composenewsapp.utils.Resource
import com.example.composenewsapp.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    private val _baseState = MutableStateFlow<BaseState>(BaseState.Empty)
    val baseState: StateFlow<BaseState> get() = _baseState
    var isFirstTime: Boolean = true

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        // _baseState.value = _baseState.value.copy(baseState = BaseState.Error(UiText.DynamicString(exception.message.toString())))
        _baseState.value = BaseState.Error(errorMessage = UiText.DynamicString(exception.message.toString()))
    }

    fun setLoadingState() {
       //  _baseState.value = _baseState.value.copy(baseState = BaseState.Loading)
        _baseState.value = BaseState.Loading
    }

    fun setErrorState(errorMessage: UiText) {
        // _baseState.value = _baseState.value.copy(baseState = BaseState.Error(errorMessage))
        _baseState.value = BaseState.Error(errorMessage)
    }

    suspend fun <T : Any> executeUseCase(call: suspend () -> Resource<T>): Resource<T> {
        return withContext(Dispatchers.IO + exceptionHandler) {
            call.invoke()
        }
    }
}
