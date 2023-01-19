package com.example.testcompose.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.allen.inprogress.InProgress
import com.allen.mainscreen.MainScreen
import com.allen.readscreen.ReadScreen
import com.allen.boardingscreen.BoardingScreen
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
            val hasRoutes = remember(navBarStackEntry) {
                listBottomMenuNavigation.find {
                    it.route == navBarStackEntry?.destination?.route
                }
            }
            if (hasRoutes != null) {
                BottomNavigation(backgroundColor = Color.LightGray) {
                    val currentNav = navBarStackEntry?.destination?.route
                    listBottomMenuNavigation.forEach {
                        BottomNavigationItem(
                            selected = currentNav == it.route,
                            onClick = {
                                controller.navigate(it.route) {
                                    popUpTo(listBottomMenuNavigation[0].route) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(imageVector = it.icon as ImageVector, contentDescription = "")
                            },
                            unselectedContentColor = Color.Gray,
                            selectedContentColor = Color.Blue.copy(0.6F), label = {
                                Text(
                                    text = it.title.toString(),

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
            startDestination = NavigationSealedClass.MainMenu.route
        ) {
            composable(route = NavigationSealedClass.ReadMenu.route) {
                ReadScreen(controller = controller)
            }
            composable(route = NavigationSealedClass.MainMenu.route) {
                MainScreen(onClick = {
                    controller.navigate(NavigationSealedClass.ReadMenu.route) {
                        restoreState = true
                    }
                }, paddingContent = contentPadding)
            }
            composable(route = NavigationSealedClass.CreateMenu.route) {
                InProgress()
            }
            composable(route = NavigationSealedClass.SettingsMenu.route) {
                InProgress()
            }
            composable(route = NavigationSealedClass.ExploreMenu.route) {
                InProgress()
            }
            composable(route = NavigationSealedClass.SavedMenu.route) {
                InProgress()
            }
            composable(route = NavigationSealedClass.SelectionMenu.route) {
                BoardingScreen(popUpNavigatorClick = {
                    controller.navigateToMainScreen()
                })
            } 
        }
    }
}
