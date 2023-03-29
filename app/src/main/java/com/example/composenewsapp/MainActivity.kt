package com.example.composenewsapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composenewsapp.domain.models.NewsQuery
import com.example.composenewsapp.presentation.base.BaseActivity
import com.example.composenewsapp.presentation.news.NewsViewModel
import com.example.composenewsapp.presentation.theme.ComposeNewsAppTheme
import com.example.composenewsapp.presentation.theme.Purple500
import com.example.composenewsapp.screen.ArticleList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()

            val visibleSearchBar = rememberSaveable { mutableStateOf(false) }
            val searchStatement = rememberSaveable { mutableStateOf("") }
            val focusRequester = remember { FocusRequester() }

            ComposeNewsAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
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
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            if(visibleSearchBar.value){
                                SearchBar(searchStatement = searchStatement, focusRequester = focusRequester)

                                LaunchedEffect(key1 = Unit){
                                    focusRequester.requestFocus()
                                }
                            }

                            NewsScreen(searchStatement.value, scaffoldState, it)
                        }
                    }
                }
            }
        }
    }
    
    @Composable
    fun NewsScreen(
        searchStatement: String,
        scaffoldState: ScaffoldState,
        paddingValues: PaddingValues,
        viewModel: NewsViewModel = hiltViewModel()
    ) {
        LaunchedEffect(key1 = searchStatement) {
            val query = NewsQuery(
                searchStatement = searchStatement.ifBlank { "Technology" },
            )
            delay(500)
            viewModel.requestNews(query)
        }

        val articles by viewModel.news.collectAsState()

        val state by viewModel.state.collectAsState()

        HandleUI(scaffoldState = scaffoldState, state = state)

        if(articles != null)
            ArticleList(articles = articles!!, paddingValues)

    }

    @Composable
    fun SearchBar(
        searchStatement: MutableState<String>,
        focusRequester: FocusRequester
    ){
        val focusManager = LocalFocusManager.current

        OutlinedTextField(
            value = searchStatement.value,
            onValueChange = { searchStatement.value = it },
            trailingIcon = {
                IconButton(onClick = { searchStatement.value = "" }) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription ="")
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { focusManager.clearFocus() }
            ),
            label = { Text(text = "Search") },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .padding(5.dp)
        )
    }
    
    @Composable
    fun SearchFAB(
        visibleSearchBar: MutableState<Boolean>
    ){
        FloatingActionButton(
            onClick = {
                visibleSearchBar.value = !visibleSearchBar.value
            },
            backgroundColor = Purple500,
            contentColor = Color.White
        ) {
            if(visibleSearchBar.value)
                Icon(imageVector = Icons.Filled.Close, contentDescription = "")
            else
                Icon(imageVector = Icons.Filled.Search, contentDescription = "")
        }
    }
}

