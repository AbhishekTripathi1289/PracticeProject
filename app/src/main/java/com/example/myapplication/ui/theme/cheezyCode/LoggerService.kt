package com.example.myapplication.ui.theme.cheezyCode

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class LoggerService @Inject constructor() {
    val  Tag = "LoggerService"
    fun log(message: String)
    {
        Log.d(Tag, message)
    }
}