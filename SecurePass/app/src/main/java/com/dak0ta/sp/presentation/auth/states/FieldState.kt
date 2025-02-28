package com.dak0ta.sp.presentation.auth.states

import androidx.annotation.StringRes

sealed class FieldState(@StringRes open val title: Int, open val value: String) {

    data class Valid(
        override val title: Int,
        override val value: String,
    ) : FieldState(title, value)

    data class Invalid(
        override val title: Int,
        override val value: String,
        @StringRes val errorMessage: Int,
    ) : FieldState(title, value)
}
