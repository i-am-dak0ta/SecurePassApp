package com.dak0ta.sp.di

import com.dak0ta.sp.domain.usecases.AuthorizeUseCase
import com.dak0ta.sp.domain.usecases.GetAuthTokenUseCase
import com.dak0ta.sp.domain.usecases.GetUidUseCase
import com.dak0ta.sp.domain.usecases.IsAuthorizedUseCase
import com.dak0ta.sp.domain.usecases.LogOutUseCase
import com.dak0ta.sp.domain.usecases.impl.AuthorizeUseCaseImpl
import com.dak0ta.sp.domain.usecases.impl.GetAuthTokenUseCaseImpl
import com.dak0ta.sp.domain.usecases.impl.GetUidUseCaseImpl
import com.dak0ta.sp.domain.usecases.impl.IsAuthorizedUseCaseImpl
import com.dak0ta.sp.domain.usecases.impl.LogOutUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindAuthorizeUseCase(
        impl: AuthorizeUseCaseImpl,
    ): AuthorizeUseCase

    @Binds
    @Singleton
    abstract fun bindGetAuthTokenUseCase(
        impl: GetAuthTokenUseCaseImpl,
    ): GetAuthTokenUseCase

    @Binds
    @Singleton
    abstract fun bindGetUidUseCase(
        impl: GetUidUseCaseImpl,
    ): GetUidUseCase

    @Binds
    @Singleton
    abstract fun bindIsAuthorizedUseCase(
        impl: IsAuthorizedUseCaseImpl,
    ): IsAuthorizedUseCase

    @Binds
    @Singleton
    abstract fun bindLogOutUseCase(
        impl: LogOutUseCaseImpl,
    ): LogOutUseCase
}
