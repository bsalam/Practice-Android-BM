package com.example.composenewsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            ComposeNewsAppTheme() {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            TopAppBar() {
                                Text("News App BM")
                            }
                        }
                    ) {
                        NewsScreen(scaffoldState, it)
                    }
                }
            }
        }
    }
    
@Composable
fun NewsScreen(
    scaffoldState: ScaffoldState,
    paddingValues: PaddingValues,
    viewModel: NewsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        val query = NewsQuery(
            searchStatement = "Technology",
        )
        viewModel.requestNews(query)
    }

    val articles by viewModel.news.collectAsState()

    val state by viewModel.state.collectAsState()

    if (articles == null) {
        HandleUI(scaffoldState = scaffoldState, state = basestate.value)
    } else {
        ArticleList(articles = news.value, paddingValues)
    }

}

