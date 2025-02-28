package com.dak0ta.sp.presentation.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.dak0ta.sp.presentation.home.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    HomeScreenContent(
        state = state,
        onUidTransferWithNfc = viewModel::onUidTransferWithNfc
    )
    LaunchedEffect(viewModel) {
        viewModel.initialize()
    }
}
