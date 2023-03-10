package com.example.composenewsapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.composenewsapp.presentation.base.BaseActivity
import com.example.composenewsapp.presentation.theme.ComposeNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @Composable
    override fun Content() {
        // Text(text = "hello seif")

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeNewsAppTheme {

    }
}