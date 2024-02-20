package com.example.myapplication.ui.theme.androidwidgets.models

data class Question(
    val questionText: String,
    val options: List<String>,
    val correctOption: Int,
    var userSelectedOption: Int? = null
)