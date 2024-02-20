package com.example.myapplication.ui.theme.utils

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.ui.theme.modules.Quote
import com.google.gson.Gson

object DataManager
{
    var dataList = emptyArray<Quote>()

    var isDataLoaded = mutableStateOf(false)

    var navigation = mutableStateOf(ScreenEnum.Listing)

    var currentQuote: Quote? = null
    fun loadAssetFromFile(context: Context, fileName: String)
    {

        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        dataList = gson.fromJson(json, Array<Quote>::class.java)
        isDataLoaded.value = true
    }


    fun switchScreen(quote: Quote?)
    {
        currentQuote = quote
        if(navigation.value == ScreenEnum.Listing)
        {
            navigation.value = ScreenEnum.Detail
        }
        else{
            navigation.value = ScreenEnum.Listing

        }
    }
    enum class ScreenEnum{
        Listing, Detail
    }
}