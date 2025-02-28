package com.dak0ta.sp.domain.usecases.impl

import com.dak0ta.sp.domain.repository.AppRepository
import com.dak0ta.sp.domain.usecases.GetUidUseCase
import javax.inject.Inject

class GetUidUseCaseImpl @Inject constructor(
    private val repository: AppRepository,
) : GetUidUseCase {

    override fun invoke(): String {
        return repository.getUidToken()
    }
}
