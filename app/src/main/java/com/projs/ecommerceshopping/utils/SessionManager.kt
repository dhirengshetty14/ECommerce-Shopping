package com.projs.ecommerceshopping.utils

import android.content.Context

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_NAME = "name"
        private const val KEY_EMAIL = "email"
        private const val KEY_MOBILE = "mobile"
    }

    fun saveUser(userId: String, name: String, email: String, mobile: String) {
        prefs.edit().apply {
            putBoolean(KEY_IS_LOGGED_IN, true)
            putString(KEY_USER_ID, userId)
            putString(KEY_NAME, name)
            putString(KEY_EMAIL, email)
            putString(KEY_MOBILE, mobile)
            apply()
        }
    }

    fun getUserName() = prefs.getString(KEY_NAME, "") ?: ""
    fun getEmail() = prefs.getString(KEY_EMAIL, "") ?: ""
    fun getMobile() = prefs.getString(KEY_MOBILE, "") ?: ""

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun logout() {
        prefs.edit().clear().apply()
    }
}