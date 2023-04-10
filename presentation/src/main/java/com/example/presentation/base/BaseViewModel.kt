package com.example.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.CustomExceptionDomainModel
import com.example.presentation.mapper.toCustomExceptionPresentationModel
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

    private val _baseState = MutableStateFlow<BaseState>(BaseState.Empty)
    val baseState: StateFlow<BaseState> get() =  _baseState.asStateFlow()
    var isFirstTime: Boolean = true

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _baseState.value = BaseState.Error((exception as CustomExceptionDomainModel).toCustomExceptionPresentationModel())
    }

    fun setState(newState: BaseState) {
        _baseState.value = newState
    }

    fun <T : Any> executeUseCase(call: suspend () -> T) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            call.invoke()
        }
    }
}
