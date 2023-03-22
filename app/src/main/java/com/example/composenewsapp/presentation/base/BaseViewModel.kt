package com.example.composenewsapp.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    private val _baseState = MutableStateFlow<BaseState>(BaseState.Loading)
    val baseState: StateFlow<BaseState> get() = _baseState
    var isFirstTime: Boolean = true

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("---", "in exception handler ${exception.message.toString()}")
        isFirstTime = true
        _baseState.value = BaseState.Error(errorMessage = exception.message.toString())
    }

    fun setLoadingState() {
        //  _baseState.value = _baseState.value.copy(baseState = BaseState.Loading)
        _baseState.value = BaseState.Loading
    }

    fun setErrorState(errorMessage: String) {
        // _baseState.value = _baseState.value.copy(baseState = BaseState.Error(errorMessage))
        _baseState.value = BaseState.Error(errorMessage)
    }

    suspend fun <T : Any> executeUseCase(call: suspend () -> T): T {
//         withContext(Dispatchers.IO + exceptionHandler) {
//            call.invoke().let {
//                Log.d("---", "return form excecut: ${it.toString()}")
//                when(it){
//                    is Resource.Error -> throw it.throwable
//                    is Resource.Success -> return@let it.data
//                }
//            }
//        }
        return withContext(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            // this code didn't execute
            Log.d("---", "exception: ${throwable.toString()}")
            isFirstTime = true
            _baseState.value = BaseState.Error(errorMessage = throwable.message.toString())
        }) {
            Log.d("---", "call invoke")
            try {
                call.invoke()
            }
            catch (e:Exception){
                isFirstTime = true
                _baseState.value = BaseState.Error(errorMessage = e.message.toString())
                throw e
            }
        }
    }
}
