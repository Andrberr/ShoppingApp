package com.example.shopping.data.sources

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class TokenSource @Inject constructor(
    private val prefs: SharedPreferences
) {
    init {
        setToken()
    }

    fun getToken(): String = prefs.getString(TOKEN_KEY, EMPTY_STRING).orEmpty()

    private fun setToken() = prefs.edit {
        putString(TOKEN_KEY, TOKEN)
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val TOKEN_KEY = "TOKEN_KEY"
        private const val TOKEN = "F2M3Q4"
    }
}