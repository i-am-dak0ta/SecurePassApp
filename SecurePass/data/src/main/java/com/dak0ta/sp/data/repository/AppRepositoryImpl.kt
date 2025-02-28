package com.dak0ta.sp.data.repository

import com.dak0ta.sp.data.storage.SharedPrefsStorage
import com.dak0ta.sp.domain.repository.AppRepository
import java.util.UUID
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val sharedPrefsStorage: SharedPrefsStorage,
) : AppRepository {

    override suspend fun authorize(login: String, password: String) {
        require(login == "admin" && password == "admin") { "Invalid credentials provided" }

        sharedPrefsStorage.authToken = "admin_admin_token"
        sharedPrefsStorage.uid = "NFC-${UUID.randomUUID().toString().take(8)}"
    }

    override fun getAuthToken(): String {
        return sharedPrefsStorage.authToken!!
    }

    override fun getUidToken(): String {
        return sharedPrefsStorage.uid!!
    }

    override fun isAuthorized(): Boolean {
        return sharedPrefsStorage.authToken != null
    }

    override fun logOut() {
        sharedPrefsStorage.authToken = null
        sharedPrefsStorage.uid = null
    }
}
