package com.dak0ta.sp.presentation.home

import com.dak0ta.sp.app.R
import javax.inject.Inject

class HomeUiStateMapper @Inject constructor() : (HomeState) -> HomeUiState {

    override fun invoke(state: HomeState): HomeUiState {
        return when (state) {
            is HomeState.Loading -> HomeUiState.Loading
            is HomeState.Content -> HomeUiState.Content(
                title = R.string.home_screen_title,
                buttonText = R.string.send_uid
            )

            is HomeState.Error -> HomeUiState.Error(R.string.error)
        }
    }
}
