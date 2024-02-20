package com.example.myapplication.ui.theme.utils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.gson.JsonSyntaxException

import org.junit.Test
import java.io.FileNotFoundException

class DataManagerTest {

    @Test(expected = FileNotFoundException::class)
    fun loadAssetFromFile_File_Note_Found_Exception() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        DataManager.loadAssetFromFile(context, "quot.json")
    }

    @Test(expected = JsonSyntaxException::class)
    fun loadAssetFromFile_JsonSyntaxException() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        DataManager.loadAssetFromFile(context, "quotesSecond.json")
    }
}