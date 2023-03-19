package com.example.composenewsapp.presentation.base

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composenewsapp.presentation.common_components.*

abstract class BaseActivity : ComponentActivity() {
    @Composable
    fun HandleUI(
        state: BaseState,
    ) {
        when (state) {
            is BaseState.Empty -> Unit
            is BaseState.Loading -> {
                ShowLoader()
            }
            is BaseState.Error -> {
                ShowSnackBar(message = state.errorMessage.asString())
            }
            is BaseState.NoInternetConnection -> {
                ShowNoInternetConnection(state.message.asString())
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun ShowSnackBar(
        baseViewModel:BaseViewModel = hiltViewModel(),
        message: String
    ) {

        Log.d("News", "Error Message: $message")
        val scaffoldState = rememberScaffoldState()
        if (baseViewModel.isFirstTime) {
            LaunchedEffect(key1 = message) {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short
                )
            }
            baseViewModel.isFirstTime = false
            Scaffold(
                scaffoldState = scaffoldState
            ) {}
        }

    }

    @Composable
    private fun ShowLoader() {
        Log.d("News", "loading...")
        Column {
            repeat(10) {
                AnimatedShimmer()
            }
        }
    }

    @Composable
    private fun ShowNoInternetConnection(errorMessage: String) {
        Log.d("News", "no Internet Connection")
        val windowInfo = rememberWindowInfo()
        if (errorMessage.isNotBlank()) {
            when (windowInfo.screenWidthInfo) {
                is WindowInfo.WindowType.Compact -> NoInternetConnectionSectionPortrait()
                else -> NoInternetConnectionSectionLandscape()
            }
        }
    }
}