package com.dak0ta.sp.presentation.auth.states

sealed interface AuthorizationUiState {

    object Loading : AuthorizationUiState

    data class Content(
        val header: HeaderState,
        val login: FieldState,
        val password: FieldState,
        val button: ButtonState,
    ) : AuthorizationUiState
}
