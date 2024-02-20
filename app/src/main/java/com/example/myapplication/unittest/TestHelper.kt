package com.example.myapplication.unittest

class TestHelper {

    fun isPalindrom(str: String): Boolean
    {
        val cleanedInput = str.toLowerCase().replace(Regex("[^a-z0-9]"), "")
        return cleanedInput == cleanedInput.reversed()
    }
}