package com.example.composenewsapp.presentation.base

import com.example.composenewsapp.utils.UiText

sealed class BaseState {
    object Empty : BaseState()
    object Loading : BaseState()
    data class Error(val errorMessage: String) : BaseState() // replace this errorMessage with sealed class that will be made for errorTypes

    data class NoInternetConnection(val message: UiText) : BaseState()
}

//data class BaseState (
//   val loading :Boolean = false,
//    val errorMessage: UiText = UiText.DynamicString(""),
//    val noInternetConnection: UiText = UiText.DynamicString("")
//)