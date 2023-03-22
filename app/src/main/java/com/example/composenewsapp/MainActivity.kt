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
import com.example.composenewsapp.domain.model.NewsQuery
import com.example.composenewsapp.presentation.base.BaseActivity
import com.example.composenewsapp.presentation.base.BaseViewModel
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
        viewModel: NewsViewModel = hiltViewModel(),
        baseViewModel: BaseViewModel = hiltViewModel()
    ) {
        LaunchedEffect(key1 = Unit) {
            val query = NewsQuery(
                searchStatement = "Technology",
            )
            viewModel.requestNews(query)
        }

        val news = viewModel.newsState.collectAsState()
        val basestate = baseViewModel.baseState.collectAsState()
        Log.d("---", "newsstate: $news")
        Log.d("---", "basestate: $basestate")
        // val errorMessages = viewModel.errorMessages.collectAsState(null).value

//        if (errorMessages != null) {
//            if (baseViewModel.isFirstTime) {
//                LaunchedEffect(key1 = newsState) {
//                    scaffoldState.snackbarHostState.showSnackbar(errorMessages)
//                }
//                baseViewModel.isFirstTime = false
//            }
//        }
        if (news.value.isEmpty()) {
            HandleUI(scaffoldState = scaffoldState, state = basestate.value)
        } else {
            ArticleList(articles = news.value, paddingValues)
        }
    }
}