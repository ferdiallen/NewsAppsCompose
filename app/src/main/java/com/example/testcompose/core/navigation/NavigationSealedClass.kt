package com.example.testcompose.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationSealedClass(
    val route: String,
    var icon: ImageVector? = null,
    val title: String? = ""
) {
    object Login : NavigationSealedClass("login_screen")
    object MainMenu :
        NavigationSealedClass("login_screen", title = "Home", icon = Icons.Filled.Home)

    object RegisterMenu : NavigationSealedClass("login_screen")
    object SelectionMenu : NavigationSealedClass("login_screen")
    object ReadMenu : NavigationSealedClass("login_screen")
    object SettingsMenu :
        NavigationSealedClass("settings_menu", title = "Settings", icon = Icons.Filled.Settings)

    object CreateMenu :
        NavigationSealedClass("create_post_menu", title = "Create", icon = Icons.Filled.Create)

    object ExploreMenu :
        NavigationSealedClass("create_post_menu", title = "Explore", icon = Icons.Filled.Explore)

    object SavedMenu :
        NavigationSealedClass("create_post_menu", title = "Saved", icon = Icons.Filled.Save)
}
