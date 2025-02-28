package com.dak0ta.sp.domain.usecases.impl

import com.dak0ta.sp.domain.repository.AppRepository
import com.dak0ta.sp.domain.usecases.IsAuthorizedUseCase
import javax.inject.Inject

class IsAuthorizedUseCaseImpl @Inject constructor(
    private val repository: AppRepository,
) : IsAuthorizedUseCase {

    override fun invoke(): Boolean {
        return repository.isAuthorized()
    }
}
