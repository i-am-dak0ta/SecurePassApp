package com.dak0ta.sp.presentation.home

import androidx.annotation.StringRes

sealed interface HomeUiState {

    object Loading : HomeUiState

    data class Content(
        @StringRes val title: Int,
        @StringRes val buttonText: Int,
    ) : HomeUiState

    data class Error(@StringRes val message: Int) : HomeUiState
}
