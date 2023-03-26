package com.example.composenewsapp.presentation.news

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.composenewsapp.domain.models.ArticleDomainModel
import com.example.composenewsapp.domain.models.NewsQuery
import com.example.composenewsapp.domain.use_cases.FetchNewsUseCase
import com.example.composenewsapp.presentation.base.BaseState
import com.example.composenewsapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val fetchNewsUseCase: FetchNewsUseCase,
) : BaseViewModel() {

    private val _news = MutableStateFlow(listOf<ArticleDomainModel>())
    val news: StateFlow<List<ArticleDomainModel>> get() = _news

    fun requestNews(newsQuery: NewsQuery) {
        Log.d("---", "request news $newsQuery")
        setState(BaseState.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            executeUseCase {
                _news.value = fetchNewsUseCase(newsQuery)
            }
        }
    }
}
