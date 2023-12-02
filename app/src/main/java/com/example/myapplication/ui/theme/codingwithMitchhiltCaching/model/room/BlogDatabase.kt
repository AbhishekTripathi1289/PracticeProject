package com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [BlogCacheEntity::class], version = 1)
abstract class BlogDatabase: RoomDatabase() {
    abstract fun getBlogDao(): BlogDao
    companion object
    {
        val DATABASE_NAME ="blog_db"
    }
}