package com.example.myapplication.ui.theme.room_retrofit_di_crud.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "STUDENT_TABLE")
data class Student(@ColumnInfo("id") @PrimaryKey(autoGenerate = true) var id : Int = 0, @ColumnInfo("name") var name : String)
