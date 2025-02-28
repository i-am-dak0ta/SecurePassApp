package com.dak0ta.sp.domain.repository

interface AppRepository {

    suspend fun authorize(login: String, password: String)

    fun getAuthToken(): String

    fun getUidToken(): String

    fun isAuthorized(): Boolean

    fun logOut()
}
