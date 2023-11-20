package com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "blogs")
data class BlogCacheEntity(@ColumnInfo("id") @PrimaryKey(autoGenerate = false) var id: Int,
                           @ColumnInfo("title") var title: String,
                           @ColumnInfo("body") var body: String,
                           @ColumnInfo("image") var image: String,
                           @ColumnInfo("category")  var category: String)
