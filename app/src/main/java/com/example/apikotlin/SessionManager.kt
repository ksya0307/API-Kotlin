package com.example.apikotlin

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private var prefs:SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = ""
    }

    fun saveAuthToken(token: Int) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token.toString())
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}