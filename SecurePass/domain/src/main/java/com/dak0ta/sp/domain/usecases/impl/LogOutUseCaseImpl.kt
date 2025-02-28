package com.dak0ta.sp.domain.usecases.impl

import com.dak0ta.sp.domain.repository.AppRepository
import com.dak0ta.sp.domain.usecases.LogOutUseCase
import javax.inject.Inject

class LogOutUseCaseImpl @Inject constructor(
    private val repository: AppRepository,
) : LogOutUseCase {

    override fun invoke() {
        repository.logOut()
    }
}