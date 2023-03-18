package com.example.composenewsapp

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composenewsapp.domain.model.NewsQuery
import com.example.composenewsapp.presentation.base.BaseActivity
import com.example.composenewsapp.presentation.news.NewsViewModel
import com.example.composenewsapp.screen.ArticleList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @Composable
    override fun Content() {
        NewsScreen()
    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel()
) {
    val query = NewsQuery(
        searchStatement = "Technology",
    )
    viewModel.requestNews(query)
    val articles = viewModel.news.collectAsState().value

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

