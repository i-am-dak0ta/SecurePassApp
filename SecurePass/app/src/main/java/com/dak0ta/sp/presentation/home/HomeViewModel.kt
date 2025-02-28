package com.dak0ta.sp.presentation.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dak0ta.sp.app.nfc.MyHostApduService
import com.dak0ta.sp.common.mvvm.BaseViewModel
import com.dak0ta.sp.domain.usecases.GetAuthTokenUseCase
import com.dak0ta.sp.domain.usecases.GetUidUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAuthTokenUseCase: GetAuthTokenUseCase,
    private val getUidUseCase: GetUidUseCase,
    uiStateMapper: HomeUiStateMapper,
) : BaseViewModel() {

    private val dataState = MutableStateFlow<HomeState>(HomeState.Loading)
    val uiState: StateFlow<HomeUiState> = dataState.map(uiStateMapper)
        .stateInViewModel(HomeUiState.Loading)

    override fun onFirstInit() {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            runCatching {
                coroutineScope {
                    val tokenDeferred = async { getAuthTokenUseCase() }
                    val uidDeferred = async { getUidUseCase() }

                    val token = tokenDeferred.await()
                    val uid = uidDeferred.await()

                    Pair(token, uid)
                }
            }
                .onSuccess { (token, uid) ->
                    dataState.value = HomeState.Content(
                        token = token,
                        uid = uid
                    )
                }
                .onFailure { e ->
                    Log.e(TAG, "Data loading failed", e)
                    dataState.value = HomeState.Error(e)
                }
        }
    }

    fun onUidTransferWithNfc() {
        val currentState = dataState.value
        if (currentState !is HomeState.Content) return
        val uid = currentState.uid
        MyHostApduService.currentUid = uid
    }

    companion object {

        const val TAG = "HomeViewModel"
    }
}
