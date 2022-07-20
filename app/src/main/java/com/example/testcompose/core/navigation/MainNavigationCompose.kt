package com.example.testcompose.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testcompose.presentation.main.MainScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainNavigationCompose() {
    val systemUiController = rememberSystemUiController()
    val controller = rememberNavController()
    SideEffect {
        systemUiController.setStatusBarColor(Color.White)
    }
    NavHost(navController = controller, startDestination = NavigationSealedClass.MainMenu.route) {
        composable(route = NavigationSealedClass.MainMenu.route) {
            MainScreen(controller)
        }
        composable(route = NavigationSealedClass.CreateMenu.route) {

        }
        composable(route = NavigationSealedClass.SettingsMenu.route) {

        }
        composable(route = NavigationSealedClass.ExploreMenu.route) {

        }
        composable(route = NavigationSealedClass.SavedMenu.route) {

        }
    }
}
