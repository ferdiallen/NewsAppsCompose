package com.example.testcompose.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testcompose.presentation.main.MainScreen
import com.example.testcompose.presentation.read.ReadScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainNavigationCompose() {
    val systemUiController = rememberSystemUiController()
    val controller = rememberNavController()
    SideEffect {
        systemUiController.setStatusBarColor(Color.White)
    }
    NavHost(navController = controller, startDestination = NavigationSealedClass.ReadMenu.route) {
        composable(route = NavigationSealedClass.ReadMenu.route) {
            ReadScreen(controller = controller)
        }
        composable(route = NavigationSealedClass.MainMenu.route) {
            MainScreen(controller)
        }
        composable(route = NavigationSealedClass.CreateMenu.route) {
            MainScreen(controller)
        }
        composable(route = NavigationSealedClass.SettingsMenu.route) {
            MainScreen(controller)
        }
        composable(route = NavigationSealedClass.ExploreMenu.route) {
            MainScreen(controller)
        }
        composable(route = NavigationSealedClass.SavedMenu.route) {
            MainScreen(controller)
        }
    }
}
