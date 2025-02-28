package com.dak0ta.sp.presentation.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dak0ta.sp.common.degign.widgets.ErrorMessage
import com.dak0ta.sp.common.degign.widgets.LoadingIndicator
import com.dak0ta.sp.presentation.home.HomeUiState

@Composable
fun HomeScreenContent(
    state: HomeUiState,
    onUidTransferWithNfc: () -> Unit,
) {
    when (state) {
        is HomeUiState.Loading -> {
            LoadingIndicator()
        }

        is HomeUiState.Content -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 32.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(state.title),
                    style = MaterialTheme.typography.headlineLarge,
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    onClick = onUidTransferWithNfc,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(stringResource(state.buttonText))
                }
            }
        }

        is HomeUiState.Error -> {
            ErrorMessage(stringResource(state.message))
        }
    }
}
