package com.example.composenewsapp

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.composenewsapp.presentation.base.BaseActivity
import com.example.composenewsapp.presentation.news.NewsArticle
import com.example.composenewsapp.presentation.news.NewsSource
import com.example.composenewsapp.presentation.theme.ComposeNewsAppTheme
import com.example.composenewsapp.screen.ArticleList
import dagger.hilt.android.AndroidEntryPoint

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
fun DefaultPreview() {
    ComposeNewsAppTheme {
        Scaffold(
            topBar = {
                TopAppBar() {
                    Text("News App BM")
                }
            }
        ){
            ArticleList(
                articles = emptyList()
//                articles = listOf(
//                    NewsArticle(
//                        title = "abdo",
//                        publishedAt = "2",
//                        source = NewsSource("kk")
//                    ),NewsArticle(
//                        title = "abdo",
//                        publishedAt = "2",
//                        source = NewsSource("kk")
//                    ),NewsArticle(
//                        title = "abdo",
//                        publishedAt = "2",
//                        source = NewsSource("kk"),
//                        urlToImage = "https://picsum.photos/200/300"
//                    )
//                )
            )
        }

    }
}

