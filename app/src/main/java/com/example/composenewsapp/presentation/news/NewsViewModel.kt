package com.example.composenewsapp.presentation.news

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.composenewsapp.domain.model.NewsQuery
import com.example.composenewsapp.domain.use_cases.FetchNewsUseCase
import com.example.composenewsapp.presentation.base.BaseState
import com.example.composenewsapp.presentation.base.BaseViewModel
import com.example.composenewsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val fetchNewsUseCase: FetchNewsUseCase,
) : BaseViewModel() {

    private val _newsState = MutableStateFlow<NewsState>(NewsState())
    val newsState: StateFlow<NewsState> get() = _newsState

    fun requestNews(newsQuery: NewsQuery) {
        Log.d("---", "request news $newsQuery")
        _newsState.value = _newsState.value.copy(baseState = BaseState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            executeUseCase { fetchNewsUseCase(newsQuery) }.let {
                when (it) {
                    is Resource.Success -> {
                        _newsState.value = _newsState.value.copy(news = it.data)
                    }
                    is Resource.Error -> {
                        isFirstTime = true
                        _newsState.value = _newsState.value.copy(baseState = BaseState.Error(it.message))
                    }
                }
            }
        }
    }
}
