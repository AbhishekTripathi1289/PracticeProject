package com.example.myapplication.unittest

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class ParameterizedTestPalindrom(var input: String,var  result: Boolean)
{

    @Test
    fun test()
    {
        var testHelper = TestHelper()
        val localResult = testHelper.isPalindrom(input)
        assertEquals(result, localResult )
    }

    companion object{
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}  is palindrom {1}")
        fun data(): List<Array<Any>>{
           return listOf(arrayOf("Level", true),
                arrayOf ("hello", false),
                arrayOf ("a", true),
                arrayOf ("", true))
        }
    }
}