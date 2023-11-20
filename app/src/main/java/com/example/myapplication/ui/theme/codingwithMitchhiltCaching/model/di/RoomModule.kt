package com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.room.BlogDao
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.room.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule
{
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): BlogDatabase
    {
        return Room.databaseBuilder(context, BlogDatabase::class.java, BlogDatabase.DATABASE_NAME).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDao(blogDatabase: BlogDatabase): BlogDao
    {
        return blogDatabase.getBlogDao()
    }
}