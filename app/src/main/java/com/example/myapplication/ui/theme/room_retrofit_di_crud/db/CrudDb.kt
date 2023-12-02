package com.example.myapplication.ui.theme.room_retrofit_di_crud.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.ui.theme.room_retrofit_di_crud.model.Student


@Database(entities = [Student::class], version = 1)
abstract class CrudDb : RoomDatabase()
{
    companion object
    {
        val DB_NAME = "student_db"
    }
    abstract fun getDao(): CrudDao
}