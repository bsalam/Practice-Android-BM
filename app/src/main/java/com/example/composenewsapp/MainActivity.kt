package com.example.composenewsapp

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.domain.model.NewsQuery
import com.example.composenewsapp.presentation.base.BaseActivity
import com.example.composenewsapp.presentation.news.NewsViewModel
import com.example.composenewsapp.presentation.theme.ComposeNewsAppTheme
import com.example.composenewsapp.screen.ArticleList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @Composable
    override fun Content() {
        DefaultPreview()
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun DefaultPreview(
    viewModel: NewsViewModel = hiltViewModel()
) {
    val query = NewsQuery(
        searchStatement = "Technology",
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    val articlesFlowLifecycleAware = remember(viewModel.news, lifecycleOwner) {
        viewModel.news.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val articles: List<Article> by articlesFlowLifecycleAware.collectAsState(initial = emptyList())

    viewModel.requestNews(query)

    ComposeNewsAppTheme {
        Scaffold(
            topBar = {
                TopAppBar() {
                    Text("News App BM")
                }
            }
        ){
            ArticleList(
                articles = articles,
//                listOf(
//                    Article(
//                        title = "abdo",
//                        publishedAt = "2",
//                        author = "atwa",
//                        description = "ddddddddddddddd",
//                        url = "",
//                        urlToImage = ""
//                    ),Article(
//                        title = "abdo",
//                        publishedAt = "2",
//                        author = "atwa",
//                        description = "ddddddddddddddd",
//                        url = "",
//                        urlToImage = ""
//                    ),Article(
//                        title = "abdo",
//                        publishedAt = "2",
//                        author = "atwa",
//                        description = "ddddddddddddddd",
//                        url = "",
//                        urlToImage = "https://picsum.photos/200/300"
//                    )
//                )
            )
        }

    }
}

