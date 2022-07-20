package com.example.testcompose.core.navigation

sealed class NavigationSealedClass(val route: String) {
    object Login : NavigationSealedClass("login_screen")
    object MainMenu : NavigationSealedClass("login_screen")
    object RegisterMenu : NavigationSealedClass("login_screen")
    object SelectionMenu : NavigationSealedClass("login_screen")
    object ReadMenu : NavigationSealedClass("login_screen")
    object SettingsMenu : NavigationSealedClass("settings_menu")
    object CreateMenu : NavigationSealedClass("create_post_menu")
}
