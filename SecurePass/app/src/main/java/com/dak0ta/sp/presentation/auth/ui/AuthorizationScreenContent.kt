package com.dak0ta.sp.presentation.auth.ui

import androidx.compose.runtime.Composable
import com.dak0ta.sp.common.degign.widgets.LoadingIndicator
import com.dak0ta.sp.presentation.auth.states.AuthorizationUiState
import com.dak0ta.sp.presentation.auth.ui.widgets.AuthorizationForm

@Composable
fun AuthorizationScreenContent(
    state: AuthorizationUiState,
    onLoginValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onLogInClick: () -> Unit,
) {
    when (state) {
        is AuthorizationUiState.Loading -> {
            LoadingIndicator()
        }

        is AuthorizationUiState.Content -> {
            AuthorizationForm(
                state = state,
                onLoginValueChange = onLoginValueChange,
                onPasswordValueChange = onPasswordValueChange,
                onLogInClick = onLogInClick,
            )
        }
    }
}
