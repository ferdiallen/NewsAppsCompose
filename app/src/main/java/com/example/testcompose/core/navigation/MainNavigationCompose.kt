package com.example.testcompose.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testcompose.presentation.boarding.BoardingScreen
import com.example.testcompose.presentation.main.MainScreen
import com.example.testcompose.presentation.read.ReadScreen
import com.example.testcompose.utils.fonts
import com.example.testcompose.utils.listBottomMenuNavigation
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainNavigationCompose() {
    val systemUiController = rememberSystemUiController()
    val controller = rememberNavController()
    val navBarStackEntry by controller.currentBackStackEntryAsState()
    SideEffect {
        systemUiController.setStatusBarColor(Color.White)
    }
    Scaffold(
        Modifier.fillMaxSize(), bottomBar = {
            val hasRoutes = listBottomMenuNavigation.find {
                it.route == navBarStackEntry?.destination?.route
            }
            if (hasRoutes != null) {
                BottomNavigation(backgroundColor = Color.LightGray) {
                    val currentNav = navBarStackEntry?.destination?.route
                    listBottomMenuNavigation.forEach {
                        BottomNavigationItem(
                            selected = currentNav == it.route,
                            onClick = {
                                controller.navigate(it.route) {
                                    popUpTo(controller.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            },
                            icon = {
                                Icon(imageVector = it.icon as ImageVector, contentDescription = "")
                            },
                            unselectedContentColor = Color.Gray,
                            selectedContentColor = Color.Blue.copy(0.6F), label = {
                                Text(
                                    text = it.title.toString(),
                                    fontFamily = fonts,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        )
                    }
                }

            }
        }
    ) { contentPadding ->
        NavHost(
            navController = controller,
            startDestination = NavigationSealedClass.SelectionMenu.route
        ) {
            composable(route = NavigationSealedClass.ReadMenu.route) {
                ReadScreen(controller = controller)
            }
            composable(route = NavigationSealedClass.MainMenu.route) {
                MainScreen(controller, paddingContent = contentPadding)
            }
            composable(route = NavigationSealedClass.CreateMenu.route) {
                MainScreen(controller, paddingContent = contentPadding)
            }
            composable(route = NavigationSealedClass.SettingsMenu.route) {
                MainScreen(controller, paddingContent = contentPadding)
            }
            composable(route = NavigationSealedClass.ExploreMenu.route) {
                MainScreen(controller, paddingContent = contentPadding)
            }
            composable(route = NavigationSealedClass.SavedMenu.route) {
                MainScreen(controller, paddingContent = contentPadding)
            }
            composable(route = NavigationSealedClass.SelectionMenu.route) {
                BoardingScreen(controller = controller)
            }
        }
    }
}
