package com.example.presentation.base

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.presentation.models.CustomExceptionPresentationModel
import com.example.presentation.R
import com.example.presentation.common_components.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : ComponentActivity() {


    @Composable
    fun HandleUI(
        baseState: BaseState,
        scaffoldState: ScaffoldState,
    ) {
        when (baseState) {  // TODO: For each case, navigate to its proper screen
            is BaseState.Empty -> Unit
            is BaseState.Loading -> {
                ShowLoader()
            }
            is BaseState.Error -> {   // should handle each error
                when (baseState.error) {
                    is CustomExceptionPresentationModel.Timeout -> ShowSnackBar(scaffoldState, getString(R.string.timeout_exception_message))

                    is CustomExceptionPresentationModel.NoInternetConnection -> {
                        ShowNoInternetConnection(
                            errorMessage = getString(R.string.no_internet_connection_exception_message)
                        )
                    }
                    is CustomExceptionPresentationModel.Network -> {
                        ShowSnackBar(scaffoldState, getString(R.string.network_exception_meesage))
                    }
                    is CustomExceptionPresentationModel.ServiceUnreachable -> {
                        ShowSnackBar(scaffoldState, getString(R.string.service_unreachable_exception_message))
                    }
                    is CustomExceptionPresentationModel.Unknown -> {
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