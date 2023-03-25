package com.example.composenewsapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composenewsapp.domain.models.NewsQuery
import com.example.composenewsapp.presentation.base.BaseActivity
import com.example.composenewsapp.presentation.news.NewsViewModel
import com.example.composenewsapp.presentation.theme.ComposeNewsAppTheme
import com.example.composenewsapp.screen.ArticleList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNewsAppTheme() {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NewsScreen()
                }
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun NewsScreen(
        viewModel: NewsViewModel = hiltViewModel(),
    ) {

        LaunchedEffect(key1 = Unit){
            val query = NewsQuery(
                searchStatement = "Technology",
            )

            viewModel.requestNews(query)

        }

//                    // check if there is an internet connection
//                    val connectivityObserver = NetworkConnectivityObserver(context)
//                    val status by connectivityObserver.observe().collectAsState(
//                        initial = ConnectivityObserver.Status.Unavailable
//                    )
//
//                    when(status) {
//                        ConnectivityObserver.Status.Available -> {
//                            viewModel.requestNews(query)
//                        }
//                        else -> {
//                            // navigate to NoInternetConnectionSectionPortrait()
//                        }
//                    }


        val articles by viewModel.news.collectAsState()

        val state by viewModel.state.collectAsState()

        Log.d("&&&", "Before HandleUI")
        HandleUI(state)

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

}


