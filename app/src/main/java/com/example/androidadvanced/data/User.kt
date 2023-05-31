package com.example.androidadvanced.data

import android.content.Context
import android.util.Log

class User {
    companion object {
        private var sharedPreferencesName = "UserDetails"
        private var TAG_USER_TOKEN = "TAG_USER_TOKEN"

        fun updateToken(newToken: String, context: Context) {
            with(context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE).edit()) {
                putString(TAG_USER_TOKEN, newToken)
                apply()
            }
            Log.d("Tag token", "Token saved into SharedPreferences: $newToken")
        }

        fun getToken(context: Context) =
            context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
                .getString(TAG_USER_TOKEN, "")
    }
}
// credit: Carlos Bellmont