package com.example.composenewsapp

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composenewsapp.domain.error_handler.ErrorEntity
import com.example.composenewsapp.domain.models.NewsQuery
import com.example.composenewsapp.presentation.base.BaseActivity
import com.example.composenewsapp.presentation.common_components.NoInternetConnectionSectionPortrait
import com.example.composenewsapp.presentation.news.NewsViewModel
import com.example.composenewsapp.screen.ArticleList
import com.example.composenewsapp.utils.network_connectivity.ConnectivityObserver
import com.example.composenewsapp.utils.network_connectivity.NetworkConnectivityObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @Composable
    override fun Content() {
        NewsScreen(context = applicationContext)
    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    context: Context
) {
    val query = NewsQuery(
        searchStatement = "Technology",
    )

    // check if there is an internet connection
    val connectivityObserver = NetworkConnectivityObserver(context)
    val status by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )

    when(status) {
        ConnectivityObserver.Status.Available -> {
            viewModel.requestNews(query)
            Log.d("@@@", "network status is $status")
        }
        else -> {
            // navigate to NoInternetConnectionSectionPortrait()
            Log.d("@@@", "network status is $status")
        }
    }

    val articles by viewModel.news.collectAsState()
    val error by viewModel.error.collectAsState()

    Log.d("@@@", "error status is $error")
    when(error){
        is ErrorEntity.Network -> {
            Snackbar {
                Text(text = "Network Error")
            }
        }
        is ErrorEntity.ServiceUnavailable -> {

        }
        is ErrorEntity.AccessDenied -> {

        }
        is ErrorEntity.NotFound -> {

        }
        else -> {

        }
    }

    Scaffold(
        topBar = {
            TopAppBar() {
                Text("News App BM")
            }
        }
    ) {
        ArticleList(
            articles = articles,
        )
    }
}

