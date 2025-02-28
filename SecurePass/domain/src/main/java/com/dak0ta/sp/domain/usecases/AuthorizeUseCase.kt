package com.dak0ta.sp.domain.usecases

interface AuthorizeUseCase {

    suspend operator fun invoke(login: String, password: String)
}
