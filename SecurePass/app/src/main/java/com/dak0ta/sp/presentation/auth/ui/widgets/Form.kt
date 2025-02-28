package com.dak0ta.sp.presentation.auth.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dak0ta.sp.presentation.auth.states.AuthorizationUiState

@Composable
fun Form(
    state: AuthorizationUiState.Content,
    onLoginValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputField(
            fieldState = state.login,
            onValueChange = onLoginValueChange
        )

        Spacer(modifier = Modifier.height(20.dp))

        PasswordInputField(
            fieldState = state.password,
            onValueChange = onPasswordValueChange
        )
    }
}
