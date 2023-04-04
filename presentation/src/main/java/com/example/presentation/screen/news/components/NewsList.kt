package com.example.presentation.screen.news.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.domain.models.ArticleDomainModel


@Composable
fun NewsList(
    articles: List<ArticleDomainModel>,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        items(articles) { article ->
            ArticleRow(
                article = article,
                onClick = {
                   println("-------")
                }
            )
        }
    }
}