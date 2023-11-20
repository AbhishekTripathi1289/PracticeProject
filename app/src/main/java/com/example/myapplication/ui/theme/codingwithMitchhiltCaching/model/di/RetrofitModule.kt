package com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.di

import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.retrofit.BlogRetrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideGson(): Gson{
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://open-api.xyz/placeholder/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    @Provides
    @Singleton
    fun provideRetrofitServer(retrofit: Retrofit): BlogRetrofit{
        return retrofit.create(BlogRetrofit::class.java)
    }

}