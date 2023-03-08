package com.example.composenewsapp.presentation.news

import androidx.lifecycle.viewModelScope
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

) : BaseViewModel(BaseState.Empty) {
    private val _newsState = MutableStateFlow(NewsState(baseState = BaseState.Empty))
    val newsState: StateFlow<NewsState> get() = _newsState

    fun requestNews() {
        setState(BaseState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            // safeApiCall { }
        }
    }

}