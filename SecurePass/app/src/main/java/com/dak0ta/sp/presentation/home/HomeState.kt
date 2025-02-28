package com.dak0ta.sp.presentation.home

sealed interface HomeState {

    object Loading : HomeState

    data class Content(
        val token: String,
        val uid: String,
    ) : HomeState

    data class Error(val throwable: Throwable) : HomeState
}
