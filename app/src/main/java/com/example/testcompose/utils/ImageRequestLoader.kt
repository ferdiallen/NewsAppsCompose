package com.example.testcompose.utils

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest

object ImageRequestLoader {
    fun requestImage(url: String,context:Context): ImageRequest {
        return ImageRequest.Builder(context)
            .data(url)
            .crossfade(500)
            .build()
    }
}