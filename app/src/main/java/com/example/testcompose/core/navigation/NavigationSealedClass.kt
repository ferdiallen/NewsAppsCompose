package com.example.testcompose.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

sealed class NavigationSealedClass(
    val route: String,
    var icon: ImageVector? = null,
    val title: String? = ""
) {
    object Login : NavigationSealedClass("login_screen")
    object MainMenu :
        NavigationSealedClass("login_screen", title = "Home", icon = Icons.Filled.Home)

    object RegisterMenu : NavigationSealedClass("login_screen")
    object SelectionMenu : NavigationSealedClass("selection_screen")
    object ReadMenu : NavigationSealedClass("read_screen")
    object SettingsMenu :
        NavigationSealedClass("settings_menu", title = "Settings", icon = Icons.Filled.Settings)

    object CreateMenu :
        NavigationSealedClass("create_post_menu", title = "Create", icon = Icons.Filled.Create)

    object ExploreMenu :
        NavigationSealedClass("explore_menu", title = "Explore", icon = Icons.Filled.Explore)

    object SavedMenu :
        NavigationSealedClass("saved_menu", title = "Saved", icon = Icons.Filled.Save)
}

/**
 * Navigation exclusive to boarding screen to Main Screen
 */
fun NavController.navigateToMainScreen() {
    this.navigate(NavigationSealedClass.MainMenu.route) {
        popUpTo(currentDestination?.route ?: NavigationSealedClass.SelectionMenu.route) {
            inclusive = true
        }
    }
}