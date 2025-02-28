package com.dak0ta.sp.domain.usecases.impl

import com.dak0ta.sp.domain.repository.AppRepository
import com.dak0ta.sp.domain.usecases.GetAuthTokenUseCase
import javax.inject.Inject

class GetAuthTokenUseCaseImpl @Inject constructor(
    private val repository: AppRepository,
) : GetAuthTokenUseCase {

    override fun invoke(): String {
        return repository.getAuthToken()
    }
}
