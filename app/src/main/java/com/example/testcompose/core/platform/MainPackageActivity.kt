package com.example.testcompose.core.platform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import com.example.testcompose.core.navigation.MainNavigationCompose
import com.example.testcompose.ui.theme.NewsAppsComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPackageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppsComposeTheme {
                MainNavigationCompose()
            }
        }
    }
}
