package com.example.presentation.screen.news

import com.example.domain.models.ArticleDomainModel
import com.example.domain.models.NewsQueryDomainModel
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

    private val _newsState = MutableStateFlow<List<ArticleDomainModel>?>(null)
    val newsState: StateFlow<List<ArticleDomainModel>?> get() = _newsState

    fun requestNews(newsQueryDomainModel: NewsQueryDomainModel) {
        setState(BaseState.Loading)
        executeUseCase {
            _newsState.value = fetchNewsUseCase(newsQueryDomainModel)
            setState(BaseState.Empty)
        }
    }
}
