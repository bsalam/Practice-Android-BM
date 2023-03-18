package com.example.composenewsapp.presentation.base

import androidx.lifecycle.ViewModel
import com.example.composenewsapp.utils.Resource
import com.example.composenewsapp.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    private val _myState = MutableStateFlow<BaseState>(BaseState.Empty)
    val state: StateFlow<BaseState> get() =  _myState

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _myState.value = BaseState.Error(UiText.DynamicString(exception.message.toString()))
       //  _myState.value = _myState.value.copy(baseState = BaseState.Error(UiText.DynamicString(exception.message.toString())))
    }

    fun setState(newState: BaseState) {
        // _myState.value = _myState.value.copy(baseState = newState)
        _myState.value = newState
    }

    suspend fun <T : Any> executeUseCase(call: suspend () -> Resource<T>): Resource<T> {
        return withContext(Dispatchers.IO + exceptionHandler) {
               call.invoke()
        }
    }
}
