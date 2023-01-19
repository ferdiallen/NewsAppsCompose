package com.allen.core.util

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest

object ImageRequestLoader {
    /**
     *Custom request image for Coil which adds simple crossfade effects to it
     */
    fun requestImage(url: Any?,context:Context): ImageRequest {
        return ImageRequest.Builder(context)
            .data(url)
            .crossfade(500)
            .build()
    }
}