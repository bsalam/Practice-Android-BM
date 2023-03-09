package com.example.composenewsapp.presentation.base

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.composenewsapp.presentation.common_components.*
import com.example.composenewsapp.presentation.theme.ComposeNewsAppTheme

abstract class BaseActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNewsAppTheme() {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Content()
                }
            }
        }
    }

    @Composable
    abstract fun Content()

    @Composable
    fun HandleUI(viewState: BaseState) {
        when (viewState) {
            is BaseState.Empty -> Unit
            is BaseState.Loading -> {
                ShowLoader()
            }
            is BaseState.Error -> {
                ShowSnackBar(viewState.errorMessage)
            }
            is BaseState.NoInternetConnection -> {
                ShowNoInternetConnection(viewState.message)
            }
        }
    }

    @Composable
    fun ShowSnackBar(message: String) {
        Log.d("News", "Error Message: $message")
        val scaffoldState = rememberScaffoldState()
        LaunchedEffect(key1 = message) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
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