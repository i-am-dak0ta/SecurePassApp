package com.dak0ta.sp.presentation.auth.states

import androidx.annotation.StringRes

sealed class ButtonState(@StringRes open val title: Int) {

    data class Enabled(
        override val title: Int,
    ) : ButtonState(title)

    data class Disabled(
        override val title: Int,
    ) : ButtonState(title)

    data class Loading(
        override val title: Int,
    ) : ButtonState(title)
}
