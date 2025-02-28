package com.dak0ta.sp.domain.usecases.impl

import com.dak0ta.sp.domain.repository.AppRepository
import com.dak0ta.sp.domain.usecases.AuthorizeUseCase
import javax.inject.Inject

class AuthorizeUseCaseImpl @Inject constructor(
    private val repository: AppRepository,
) : AuthorizeUseCase {

    override suspend operator fun invoke(login: String, password: String) {
        return repository.authorize(login, password)
    }
}
