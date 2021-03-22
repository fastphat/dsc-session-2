package com.example.dsc_session_2

import android.content.Context
import android.content.Context.MODE_PRIVATE

object SharedPreference {
    private const val PREF_USER_PROFILE = "user-profile"
    private const val USER_NAME = "user-name"
    private const val USER_PASSWORD = "user-password"

    fun saveEmail(context: Context, userName: String) {
        val sharedPreference = context.getSharedPreferences("PREF_NAME", MODE_PRIVATE)
        sharedPreference.edit().putString(USER_NAME, userName).apply()
    }

    fun getEmail(context: Context): String {
        val sharedPreference = context.getSharedPreferences("PREF_NAME", MODE_PRIVATE)
        return sharedPreference.getString(USER_NAME, "").orEmpty()
    }

    fun savePassword(context: Context, password: String) {
        val sharedPreference = context.getSharedPreferences("PREF_NAME", MODE_PRIVATE)
        sharedPreference.edit().putString(USER_PASSWORD, password).apply()
    }

    fun getPassword(context: Context): String {
        val sharedPreference = context.getSharedPreferences("PREF_NAME", MODE_PRIVATE)
        return sharedPreference.getString(USER_PASSWORD, "").orEmpty()
    }

}