package com.example.composenewsapp.presentation.screen.news.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.example.composenewsapp.presentation.theme.Purple500

@Composable
fun SearchFAB(
    visibleSearchBar: MutableState<Boolean>
) {
    FloatingActionButton(
        onClick = {
            visibleSearchBar.value = !visibleSearchBar.value
        },
        backgroundColor = Purple500,
        contentColor = Color.White
    ) {
        if (visibleSearchBar.value)
            Icon(imageVector = Icons.Filled.Close, contentDescription = "")
        else
            Icon(imageVector = Icons.Filled.Search, contentDescription = "")
    }
}