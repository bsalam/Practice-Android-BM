package com.example.composenewsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composenewsapp.domain.model.NewsQuery
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

    @Composable
    fun NewsScreen(
        viewModel: NewsViewModel = hiltViewModel()) {
        LaunchedEffect(key1 = Unit) {
            val query = NewsQuery(
                searchStatement = "Technology",
            )
            viewModel.requestNews(query)
        }

        val newsState = viewModel.newsState.collectAsState().value
        if (newsState.news.isEmpty()) {
            HandleUI(state = newsState.baseState)
        } else {
            Scaffold(
                topBar = {
                    TopAppBar() {
                        Text("News App BM")
                    }
                }
            ) {
                ArticleList(
                    articles = newsState.news,
                    paddingValues = it
                )
            }
        }
    }
}