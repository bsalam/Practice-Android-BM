package com.example.composenewsapp.presentation.news

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.domain.model.NewsQuery
import com.example.composenewsapp.domain.use_cases.FetchNewsUseCase
import com.example.composenewsapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val fetchNewsUseCase: FetchNewsUseCase,
) : BaseViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("---", "in newsViewModel exception handler ${exception.message.toString()}")
    }
    private val _newsState = MutableStateFlow<List<Article>>(emptyList())
    val newsState: StateFlow<List<Article>> get() = _newsState

//    private val _errorMessages = MutableSharedFlow<String>()
//    val errorMessages: SharedFlow<String> = _errorMessages

    fun requestNews(newsQuery: NewsQuery) {
        //Log.d("---", "request news $newsQuery")
       //  _newsState.value = _newsState.value.copy(baseState = BaseState.Loading)
        setLoadingState()
        viewModelScope.launch(Dispatchers.IO+exceptionHandler) {
            executeUseCase { fetchNewsUseCase(newsQuery) }.let {
                _newsState.value = it
            }
        }
    }
}
