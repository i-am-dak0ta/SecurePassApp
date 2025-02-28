package com.dak0ta.sp.presentation.auth.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dak0ta.sp.presentation.auth.states.AuthorizationUiState
import com.dak0ta.sp.presentation.auth.states.ButtonState

@Composable
fun AuthorizationForm(
    state: AuthorizationUiState.Content,
    onLoginValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onLogInClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            state = state.header,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        Form(
            state = state,
            onLoginValueChange = onLoginValueChange,
            onPasswordValueChange = onPasswordValueChange,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            onClick = onLogInClick,
            enabled = state.button is ButtonState.Enabled,
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 16.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(stringResource(state.button.title))
        }
    }
}
