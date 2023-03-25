package com.example.composenewsapp.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.composenewsapp.domain.models.ArticleDomainModel
import com.example.composenewsapp.presentation.common_components.HeightSpacer
import com.example.composenewsapp.presentation.common_components.RemoteImage
import com.example.composenewsapp.presentation.common_components.WidthSpacer

@Composable
fun ArticleRow(article: ArticleDomainModel, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable(onClick = { onClick() })) {
        Row(
            modifier = Modifier.padding(all = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RemoteImage(
                url = article.urlToImage,
                modifier = Modifier.requiredSize(100.dp)
            )
            WidthSpacer(value = 10.dp)
            Column {
                Text(
                    text = article.author
                )
                HeightSpacer(value = 4.dp)
                Text(
                    text = article.title,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                HeightSpacer(value = 4.dp)
                Text(
                    text = article.publishedAt
                )
            }
        }
        HeightSpacer(value = 10.dp)
        Divider(
            color = MaterialTheme.colors.secondary.copy(
                alpha = 0.2f
            )
        )
    }
}

@Composable
fun ArticleList(
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