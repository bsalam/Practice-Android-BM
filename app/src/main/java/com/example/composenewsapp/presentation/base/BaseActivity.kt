package com.example.composenewsapp.presentation.base

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.composenewsapp.presentation.common_components.*

abstract class BaseActivity : ComponentActivity() {
    @Composable
    fun HandleUI(
        state: BaseState,
        scaffoldState: ScaffoldState,
    ) {
        when (state) {
            is BaseState.Empty -> Unit
            is BaseState.Loading -> {
                ShowLoader()
            }
            is BaseState.Error -> {
               ShowSnackBar( scaffoldState = scaffoldState, message = state.errorMessage)
            }
            is BaseState.NoInternetConnection -> {
                ShowNoInternetConnection(state.message.asString())
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun ShowSnackBar(
        scaffoldState: ScaffoldState,
        message: String,
        baseViewModel:BaseViewModel = hiltViewModel()
    ) {

        Log.d("---", "Error Message: $message")
        if (baseViewModel.isFirstTime) {
            lifecycleScope.launchWhenStarted {
                //newsViewModel.errorMessages.collectLatest {
                    scaffoldState.snackbarHostState.showSnackbar(message)
                //}
                baseViewModel.isFirstTime = false
            }
        }
    }

    @Composable
    private fun ShowLoader() {
        Log.d("---", "loading...")
        Column {
            repeat(10) {
                AnimatedShimmer()
            }
        }
    }

    @Composable
    private fun ShowNoInternetConnection(errorMessage: String) {
        Log.d("---", "no Internet Connection")
        val windowInfo = rememberWindowInfo()
        if (errorMessage.isNotBlank()) {
            when (windowInfo.screenWidthInfo) {
                is WindowInfo.WindowType.Compact -> NoInternetConnectionSectionPortrait()
                else -> NoInternetConnectionSectionLandscape()
            }
        }
    }
}