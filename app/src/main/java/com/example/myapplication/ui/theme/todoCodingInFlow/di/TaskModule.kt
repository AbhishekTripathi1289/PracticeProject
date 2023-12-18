package com.example.myapplication.ui.theme.todoCodingInFlow.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.myapplication.ui.theme.todoCodingInFlow.room.TaskDao
import com.example.myapplication.ui.theme.todoCodingInFlow.room.TaskDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TaskModule
{
    @Provides
    fun getRoomDb(app: Application,
                  callback: TaskDataBase.Callback): TaskDataBase
    {
      return   Room.databaseBuilder(app, TaskDataBase::class.java, TaskDataBase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }
    @Provides
    fun providesTaskDao(taskDataBase: TaskDataBase): TaskDao
    {
        return taskDataBase.getTaskDao()
    }


    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope

}