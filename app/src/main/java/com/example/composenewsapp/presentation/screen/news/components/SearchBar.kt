package com.example.composenewsapp.presentation.screen.news.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    searchStatement: MutableState<String>,
    focusRequester: FocusRequester
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = searchStatement.value,
        onValueChange = { searchStatement.value = it },
        trailingIcon = {
            IconButton(onClick = { searchStatement.value = "" }) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "")
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