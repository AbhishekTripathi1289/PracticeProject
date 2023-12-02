package com.example.myapplication.ui.theme.room_retrofit_di_crud.di

import com.example.myapplication.ui.theme.room_retrofit_di_crud.retrofit.BlogApiSecond
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule
{
    @Named("gson1")
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }


    @Named("retrofit1")
    @Provides
    @Singleton
    fun provides(@Named("gson1") gson: Gson): Retrofit
    {
        return Retrofit
            .Builder()
            .baseUrl("https://open-api.xyz/placeholder/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun providesBlogsApi(@Named("retrofit1") retrofit: Retrofit): BlogApiSecond{
        return retrofit.create(BlogApiSecond::class.java)
    }
}