package com.dak0ta.sp.presentation.auth

import com.dak0ta.sp.navigation.Screen

sealed interface AuthorizationAction {

    class NavigateToHome(val route: String = Screen.Home.route) : AuthorizationAction
}
