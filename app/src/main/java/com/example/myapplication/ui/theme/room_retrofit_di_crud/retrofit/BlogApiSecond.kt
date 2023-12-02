package com.example.myapplication.ui.theme.room_retrofit_di_crud.retrofit

import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.retrofit.BlogNetworkEntity
import retrofit2.http.GET

interface BlogApiSecond
{
    @GET("blogs")
    suspend fun getBlogs(): List<BlogNetworkEntity>
}