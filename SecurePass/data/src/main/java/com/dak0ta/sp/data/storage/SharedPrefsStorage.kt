package com.dak0ta.sp.data.storage

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPrefsStorage @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var authToken: String?
        get() = sharedPreferences.getString(KEY_AUTH_TOKEN, null)
        set(value) {
            sharedPreferences.edit().putString(KEY_AUTH_TOKEN, value).apply()
        }

    var uid: String?
        get() = sharedPreferences.getString(KEY_UID, null)
        set(value) {
            sharedPreferences.edit().putString(KEY_UID, value).apply()
        }

    companion object {
        private const val PREF_NAME = "auth_prefs"
        private const val KEY_AUTH_TOKEN = "auth_token"
        private const val KEY_UID = "uid"
    }
}
