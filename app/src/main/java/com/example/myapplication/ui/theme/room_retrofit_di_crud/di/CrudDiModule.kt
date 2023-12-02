package com.example.myapplication.ui.theme.room_retrofit_di_crud.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.ui.theme.room_retrofit_di_crud.db.CrudDao
import com.example.myapplication.ui.theme.room_retrofit_di_crud.db.CrudDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object CrudDiModule
{
    @Provides
    fun provideData(@ApplicationContext applicationContext: Context): CrudDb
    {
       return  Room.databaseBuilder(applicationContext, CrudDb::class.java, CrudDb.DB_NAME).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideRoomDbDao(crudDb: CrudDb): CrudDao
    {
        return crudDb.getDao()
    }
}