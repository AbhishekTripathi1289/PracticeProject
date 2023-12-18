package com.example.myapplication.ui.theme.NotesCheezy.di

import com.example.myapplication.ui.theme.NotesCheezy.api.NotesApi
import com.example.myapplication.ui.theme.NotesCheezy.api.UserApi
import com.example.myapplication.ui.theme.NotesCheezy.utils.ApiInterceptor
import com.example.myapplication.ui.theme.NotesCheezy.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class UserModule {
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder
    {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Constants.BASE_URL)
    }
    @Singleton
    @Provides
    fun providesUserAPi(retrofit: Retrofit.Builder): UserApi
    {
        return retrofit.build().create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkhttp(apiInterceptor: ApiInterceptor): OkHttpClient
    {
        return OkHttpClient.Builder().addInterceptor(apiInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideNotesApi(retrofit: Retrofit.Builder, okHttpClient: OkHttpClient): NotesApi
    {
        return retrofit.client(okHttpClient).build().create(NotesApi::class.java)
    }
}