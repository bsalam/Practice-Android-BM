package com.example.composenewsapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.focus.FocusRequester
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composenewsapp.domain.models.NewsQuery
import com.example.composenewsapp.presentation.base.BaseActivity
import com.example.composenewsapp.presentation.screen.news.NewsViewModel
import com.example.composenewsapp.presentation.theme.ComposeNewsAppTheme
import com.example.composenewsapp.presentation.screen.news.components.NewsList
import com.example.composenewsapp.presentation.screen.news.components.SearchBar
import com.example.composenewsapp.presentation.screen.news.components.SearchFAB
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            val visibleSearchBar = rememberSaveable { mutableStateOf(false) }

            ComposeNewsAppTheme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar {
                            Text("News App BM")
                        }
                    },
                    floatingActionButton = { SearchFAB(visibleSearchBar = visibleSearchBar) },
                    floatingActionButtonPosition = FabPosition.End,
                ) {
                    NewsScreen(scaffoldState, it, visibleSearchBar)
                }
            }
        }
    }

    @Composable
    fun NewsScreen(
        scaffoldState: ScaffoldState,
        paddingValues: PaddingValues,
        visibleSearchBar: MutableState<Boolean>,
        viewModel: NewsViewModel = hiltViewModel(),
    ) {
        val focusRequester = remember { FocusRequester() }
        val searchStatement = rememberSaveable { mutableStateOf("") }

        if (visibleSearchBar.value) {
            SearchBar(
                searchStatement = searchStatement,
                focusRequester = focusRequester
            )

            LaunchedEffect(key1 = Unit) {
                focusRequester.requestFocus()
            }
        }

        LaunchedEffect(key1 = searchStatement) {
            val query = NewsQuery(
                searchStatement = searchStatement.value.ifBlank { "Technology" },
            )
            delay(500)
            viewModel.requestNews(query)
        }

        val articles by viewModel.news.collectAsState()
        val state by viewModel.state.collectAsState()

        HandleUI(scaffoldState = scaffoldState, state = state)

        if (articles != null)
            NewsList(articles = articles!!, paddingValues)

    }
}

