package com.example.presentation.screen.news

import com.example.domain.models.ArticleDomainModel
import com.example.domain.models.NewsQueryDomainModel
import com.example.domain.use_cases.FetchNewsUseCase
import com.example.presentation.base.BaseState
import com.example.presentation.base.BaseViewModel
import com.example.presentation.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val fetchNewsUseCase: FetchNewsUseCase,
    dispatcher: DispatcherProvider
) : BaseViewModel(dispatcher) {
    private val _newsState = MutableStateFlow<List<ArticleDomainModel>?>(null)
    val newsState get() = _newsState.asStateFlow()
    fun requestNews(newsQueryDomainModel: NewsQueryDomainModel) {
        setState(BaseState.Loading)
        executeUseCase {
            _newsState.value = fetchNewsUseCase(newsQueryDomainModel)
            delay(100L) // for testing purposes
            setState(BaseState.Empty)
        }
    }
}
