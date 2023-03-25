package com.example.composenewsapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenewsapp.domain.exception_handler.CustomException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    private val _myState = MutableStateFlow<BaseState>(BaseState.Empty)
    val state: StateFlow<BaseState> get() =  _myState.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _myState.value = BaseState.Error((exception as CustomException).toError())
    }

    fun setState(newState: BaseState) {
        // _myState.value = _myState.value.copy(baseState = newState)
        _myState.value = newState
    }

    suspend fun <T : Any> executeUseCase(call: suspend () -> T) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
               call.invoke()
        }
    }
}
