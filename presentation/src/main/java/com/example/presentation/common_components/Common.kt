package com.example.presentation.common_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.presentation.R

@Composable
fun HeightSpacer(value: Dp) {
    Spacer(modifier = Modifier.requiredHeight(value))
}

@Composable
fun WidthSpacer(value: Dp) {
    Spacer(modifier = Modifier.requiredWidth(value))
}

@Composable
fun RemoteImage(
    url: String?,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    shape: Shape = RoundedCornerShape(5.dp)
) {
    Box(
        modifier = modifier
    ) {
        if (url.isNullOrEmpty()) {
            Icon(
                painter = painterResource(id = R.drawable.ic_newzz_error),
                contentDescription = "error image"
            )
        } else {
            Surface(
                color = Color.Transparent,
                shape = shape
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = url)
                            .apply(block = fun ImageRequest.Builder.() {
                                crossfade(true)
                                placeholder(R.drawable.ic_newzz_error)
                            }).build()
                    ),
                    contentScale = contentScale,
                    contentDescription = "article image",
                    modifier = modifier.fillMaxSize()
                )
            }
        }
    }
}