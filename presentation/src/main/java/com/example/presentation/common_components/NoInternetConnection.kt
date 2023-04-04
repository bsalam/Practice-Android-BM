package com.example.presentation.common_components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.theme.LightGreen
import com.example.presentation.theme.LightWhite

@Composable
fun NoInternetConnectionSectionPortrait(
    // newsViewModel: NewsViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightWhite)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.nointernet_connection),
            contentDescription = "No internet connection image",
            Modifier
                .padding(top = 120.dp, bottom = 25.dp)
                .fillMaxWidth()
                .height(250.dp)
        )
        Text(
            text = "Something Went Wrong..",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 10.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "An alien is probably blocking your signal",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center

        )
        Spacer(modifier = Modifier.height(80.dp))

        OutlinedButton(
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
            border = BorderStroke(2.dp, LightGreen),
            modifier = Modifier
                .fillMaxWidth(0.8f),
            onClick = {
                // newsViewModel.forceFetchingData()
            }
        ) {
            Text(
                text = "Retry",
                fontSize = 18.sp,
                color = LightGreen
            )
        }
    }
}

@Composable
fun NoInternetConnectionSectionLandscape(
    // newsViewModel: NewsViewModel = hiltViewModel()
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(LightWhite)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Something Went Wrong..",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(bottom = 15.dp)
            )

            Text(
                text = "An alien is probably blocking your signal",
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(55.dp))

            OutlinedButton(
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
                border = BorderStroke(2.dp, LightGreen),
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                onClick = {
                    // newsViewModel.forceFetchingData()
                }
            ) {
                Text(
                    text = "Retry",
                    fontSize = 18.sp,
                    color = LightGreen
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.nointernet_connection),
            contentDescription = "No internet connection image",
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(250.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun NoInternetConnectionSectionPortraitPreview() {
    NoInternetConnectionSectionPortrait()
}

