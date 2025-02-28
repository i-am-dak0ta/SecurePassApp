package com.dak0ta.sp.presentation.auth.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.dak0ta.sp.navigation.LocalNavController
import com.dak0ta.sp.navigation.Screen
import com.dak0ta.sp.presentation.auth.AuthorizationAction
import com.dak0ta.sp.presentation.auth.AuthorizationViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun AuthorizationScreen(viewModel: AuthorizationViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    val navController = LocalNavController.current
    AuthorizationScreenContent(
        state = state,
        onLoginValueChange = viewModel::onLoginValueChange,
        onPasswordValueChange = viewModel::onPasswordValueChange,
        onLogInClick = viewModel::onLogInClick,
    )
    LaunchedEffect(viewModel) {
        viewModel.initialize()

        viewModel.action
            .onEach { action ->
                when (action) {
                    is AuthorizationAction.NavigateToHome -> {
                        navController.navigate(action.route) {
                            popUpTo(Screen.Authorization.route) { inclusive = true }
                        }
                    }
                }
            }
            .launchIn(this)
    }
}
