package com.dak0ta.sp.presentation.auth

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dak0ta.sp.app.R
import com.dak0ta.sp.common.mvvm.BaseViewModel
import com.dak0ta.sp.domain.usecases.AuthorizeUseCase
import com.dak0ta.sp.domain.usecases.IsAuthorizedUseCase
import com.dak0ta.sp.presentation.auth.states.AuthorizationUiState
import com.dak0ta.sp.presentation.auth.states.ButtonState
import com.dak0ta.sp.presentation.auth.states.FieldState
import com.dak0ta.sp.presentation.auth.states.HeaderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val authorizeUseCase: AuthorizeUseCase,
    private val isAuthorizedUseCase: IsAuthorizedUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<AuthorizationUiState>(
        AuthorizationUiState.Loading
    )
    val uiState: StateFlow<AuthorizationUiState> = _uiState
    private val _action = Channel<AuthorizationAction>(Channel.BUFFERED)
    val action: Flow<AuthorizationAction> = _action.receiveAsFlow()

    override fun onFirstInit() {
        viewModelScope.launch {
            if (isAuthorizedUseCase()) {
                _action.send(AuthorizationAction.NavigateToHome())
            } else {
                _uiState.update {
                    AuthorizationUiState.Content(
                        header = HeaderState(
                            title = R.string.auth_header_title,
                            subtitle = R.string.auth_header_subtitle,
                        ),
                        login = FieldState.Valid(
                            title = R.string.login_title,
                            value = ""
                        ),
                        password = FieldState.Valid(
                            title = R.string.password_title,
                            value = ""
                        ),
                        button = ButtonState.Disabled(title = R.string.log_in_title)
                    )
                }
            }
        }
    }

    fun onLoginValueChange(newValue: String) {
        _uiState.update { currentState ->
            if (currentState !is AuthorizationUiState.Content) return
            val newLoginState = updateFieldState(
                currentFieldState = currentState.login,
                newValue = newValue
            )
            val newButtonState = updateButtonState(
                loginState = newLoginState,
                passwordState = currentState.password
            )

            currentState.copy(
                login = newLoginState,
                button = newButtonState
            )
        }
    }

    fun onPasswordValueChange(newValue: String) {
        _uiState.update { currentState ->
            if (currentState !is AuthorizationUiState.Content) return
            val newPasswordState = updateFieldState(
                currentFieldState = currentState.password,
                newValue = newValue
            )
            val newButtonState = updateButtonState(
                loginState = currentState.login,
                passwordState = newPasswordState
            )

            currentState.copy(
                password = newPasswordState,
                button = newButtonState
            )
        }
    }

    fun onLogInClick() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                if (currentState is AuthorizationUiState.Content) {
                    currentState.copy(button = ButtonState.Loading(title = R.string.loading_title))
                } else {
                    currentState
                }
            }

            if (_uiState.value is AuthorizationUiState.Content) {
                val login = (_uiState.value as AuthorizationUiState.Content).login.value
                val password = (_uiState.value as AuthorizationUiState.Content).password.value

                runCatching {
                    delay(3000)
                    authorizeUseCase(login, password)
                }
                    .onSuccess {
                        _action.send(AuthorizationAction.NavigateToHome())
                    }
                    .onFailure { e ->
                        Log.e(TAG, "AuthorizeUseCase has failed", e)
                        _uiState.update { currentState ->
                            if (currentState is AuthorizationUiState.Content) {
                                currentState.copy(
                                    login = FieldState.Invalid(
                                        currentState.login.title,
                                        currentState.login.value,
                                        R.string.error_incorrect_login
                                    ),
                                    password = FieldState.Invalid(
                                        currentState.password.title,
                                        currentState.password.value,
                                        R.string.error_incorrect_password
                                    ),
                                    button = updateButtonState(
                                        loginState = currentState.login,
                                        passwordState = currentState.password
                                    )
                                )
                            } else {
                                currentState
                            }
                        }
                    }
            }
        }
    }

    private fun updateFieldState(currentFieldState: FieldState, newValue: String): FieldState {
        return checkLengthConstraints(newValue)?.let {
            FieldState.Invalid(currentFieldState.title, newValue, it)
        } ?: FieldState.Valid(currentFieldState.title, newValue)
    }

    private fun checkLengthConstraints(value: String): Int? = when {
        value.isEmpty() -> R.string.error_field_empty
        value.length < 2 -> R.string.error_field_too_short
        value.length > 25 -> R.string.error_field_too_long
        else -> null
    }

    private fun updateButtonState(loginState: FieldState, passwordState: FieldState): ButtonState {
        val isLoginValid = loginState is FieldState.Valid && loginState.value.isNotBlank()
        val isPasswordValid = passwordState is FieldState.Valid && passwordState.value.isNotBlank()

        return if (isLoginValid && isPasswordValid) {
            ButtonState.Enabled(title = R.string.log_in_title)
        } else {
            ButtonState.Disabled(title = R.string.log_in_title)
        }
    }

    companion object {

        const val TAG = "AuthorizationViewModel"
    }
}
