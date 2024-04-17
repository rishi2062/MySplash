package com.example.mysplash.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.mysplash.R

@Composable
fun RemoteImage(
    modifier: Modifier = Modifier,
    imageLink: String,
    contentScale: ContentScale = ContentScale.Fit,
    @DrawableRes error: Int = R.drawable.error_image,
    onError: ((AsyncImagePainter.State.Error) -> Unit)? = null,
) {
    AsyncImage(
        modifier = modifier,
        model = imageLink,
        contentDescription = null,
        contentScale = contentScale,
        error = if (error != -1) painterResource(id = error) else null,
        placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
        onError = onError
    )
}