package com.example.composenewsapp.presentation.news

import androidx.lifecycle.viewModelScope
import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.domain.model.BreakingNewsQuery
import com.example.composenewsapp.domain.model.NewsQuery
import com.example.composenewsapp.domain.use_cases.BreakingNewsUseCase
import com.example.composenewsapp.domain.use_cases.NewsUseCase
import com.example.composenewsapp.presentation.base.BaseState
import com.example.composenewsapp.presentation.base.BaseViewModel
import com.example.composenewsapp.utils.Resource
import com.example.composenewsapp.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase,
    private val breakingNewsUseCase: BreakingNewsUseCase
) : BaseViewModel(BaseState.Empty) {

    private val _newsState = MutableStateFlow(NewsState(state = BaseState.Empty))
    val newsState: StateFlow<NewsState> get() = _newsState

    private val _news = MutableStateFlow(listOf<Article>())
    val news: StateFlow<List<Article>> get() = _news

    private val _breakingNews = MutableStateFlow(listOf<Article>())
    val breakingNews: StateFlow<List<Article>> get() = _news

    fun requestNews(newsQuery: NewsQuery) = viewModelScope.launch(Dispatchers.IO) {
         newsUseCase(newsQuery).collect {
             when(it){
                 is Resource.Loading -> setState(BaseState.Loading)
                 is Resource.Success -> _news.value = it.data!!
                 is Resource.Error -> setState(BaseState.Error((it.message as UiText.DynamicString).value)) //need to change datatype of error message
             }
         }
    }

    fun requestBreakingNews(breakingNewsQuery: BreakingNewsQuery) = viewModelScope.launch(Dispatchers.IO) {
        breakingNewsUseCase(breakingNewsQuery).collect {
            when(it){
                is Resource.Loading -> setState(BaseState.Loading)
                is Resource.Success -> _breakingNews.value = it.data!!
                is Resource.Error -> setState(BaseState.Error((it.message as UiText.DynamicString).value)) //need to change datatype of error message
            }
        }
    }
}
