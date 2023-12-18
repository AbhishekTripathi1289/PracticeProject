package com.example.myapplication.ui.theme.NotesCheezy.utils

import android.content.Context
import com.example.myapplication.ui.theme.NotesCheezy.utils.Constants.PREFS_TOKEN_FILE
import com.example.myapplication.ui.theme.NotesCheezy.utils.Constants.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject  constructor(@ApplicationContext val context: Context) {

    val pref = context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)


    fun saveToken(token: String)
    {
        pref.edit().putString(USER_TOKEN, token).apply()
    }

    fun getToken(): String?
    {
        return pref.getString(USER_TOKEN, null)
    }

}