package com.example.composenewsapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenewsapp.utils.Resource
import com.example.composenewsapp.utils.UiText
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class BaseViewModel(initialState: BaseState) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<BaseState> get() =  _state

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _state.value = BaseState.Error(exception.message.toString())
    }

    fun setState(newState: BaseState) {
        viewModelScope.launch(exceptionHandler) {
            _state.value = newState
        }
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> T): Resource<T> {
        return try {
            val response = call.invoke()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(UiText.DynamicString(e.message.toString()))
        }
    }
}
