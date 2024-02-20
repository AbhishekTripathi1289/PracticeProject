package com.example.myapplication.ui.theme.imageSearchCodingInFLow.di

import com.example.myapplication.ui.theme.imageSearchCodingInFLow.api.UnsplashApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ImageSearchModule
{


    @Singleton
    @Provides
    @Named("retro1")
    fun providesRetrofit(): Retrofit.Builder{
        return Retrofit.Builder().baseUrl(UnsplashApi.BASE_URL).addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun providesImageSearchAPi(@Named("retro1") retrofitBuilder: Retrofit.Builder): UnsplashApi
    {
     return retrofitBuilder.build().create(UnsplashApi::class.java)
    }
}