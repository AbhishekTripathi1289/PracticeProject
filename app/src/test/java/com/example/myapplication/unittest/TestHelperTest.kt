package com.example.myapplication.unittest

import org.junit.Assert.*

import org.junit.Test

class TestHelperTest {

    @Test
    fun check_Is_Palindrom_To_Result_True()
    {
        var testHelper = TestHelper()
        var result = testHelper.isPalindrom("")
        assertEquals(true, result)
    }

    @Test
    fun check_Is_Palindrom_To_Result_False()
    {
        var testHelper = TestHelper()
        var result = testHelper.isPalindrom("d")
        assertEquals(true, result)
    }
}