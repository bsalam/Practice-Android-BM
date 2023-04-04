package com.example.presentation.screen.news

import android.util.Log
import com.example.domain.models.ArticleDomainModel
import com.example.domain.models.NewsQuery
import com.example.domain.use_cases.FetchNewsUseCase
import com.example.presentation.base.BaseState
import com.example.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val fetchNewsUseCase: FetchNewsUseCase,
) : BaseViewModel() {

    private val _news = MutableStateFlow<List<ArticleDomainModel>?>(null)
    val news: StateFlow<List<ArticleDomainModel>?> get() = _news

    fun requestNews(newsQuery: NewsQuery) {
        setState(BaseState.Loading)
        executeUseCase {
            _news.value = fetchNewsUseCase(newsQuery)
            if(_news.value != null && _news.value!!.isEmpty())
                setState(BaseState.NotFound)
            else
                setState(BaseState.Empty)

            Log.d("&&&", "after use case invocation and state = ${state.value}")
        }
    }
}
