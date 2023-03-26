package com.example.composenewsapp.presentation.base

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.example.composenewsapp.domain.exception_handler.ErrorEntity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.composenewsapp.R
import com.example.composenewsapp.presentation.common_components.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            is BaseState.Error -> {   // should handle each error
                when (state.error) {
                    is ErrorEntity.Timeout -> ShowSnackBar(scaffoldState, getString(R.string.timeout_exception_message))

                    is ErrorEntity.NoInternetConnection -> {
                        ShowNoInternetConnection(
                            errorMessage = getString(R.string.no_internet_connection_exception_message)
                        )
                    }
                    is ErrorEntity.Network -> {
                        ShowSnackBar(scaffoldState, getString(R.string.network_exception_meesage))
                    }
                    is ErrorEntity.ServiceUnreachable -> {
                        ShowSnackBar(scaffoldState, getString(R.string.service_unreachable_exception_message))
                    }
                    is ErrorEntity.Unknown -> {
                        ShowSnackBar(scaffoldState, getString(R.string.unknown_exception_message))
                    }
                }
            }
        }
    }


    @Composable
    fun ShowSnackBar(
        scaffoldState: ScaffoldState,
        message: String,
        baseViewModel: BaseViewModel = hiltViewModel()
    ) {

        Log.d("---", "Error Message: $message")
        if (baseViewModel.isFirstTime) {
            lifecycleScope.launchWhenStarted {
                scaffoldState.snackbarHostState.showSnackbar(message)
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