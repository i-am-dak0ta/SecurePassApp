package com.dak0ta.sp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dak0ta.sp.presentation.auth.ui.AuthorizationScreen
import com.dak0ta.sp.presentation.home.ui.HomeScreen

sealed class Screen(val route: String) {
    data object Authorization : Screen("authorization")
    data object Home : Screen("home")
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = Screen.Authorization.route,
            modifier = modifier
        ) {
            composable(route = Screen.Authorization.route) {
                AuthorizationScreen()
            }

            composable(route = Screen.Home.route) {
                HomeScreen()
            }
        }
    }
}
