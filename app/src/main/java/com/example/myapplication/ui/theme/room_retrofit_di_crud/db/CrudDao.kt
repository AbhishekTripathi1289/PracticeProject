package com.example.myapplication.ui.theme.room_retrofit_di_crud.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.ui.theme.room_retrofit_di_crud.model.Student
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



@Dao
interface CrudDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(student: Student):Long


    /*Room with LiveData*/
    @Query("SELECT * FROM student_table")
    fun getAllStudent(): LiveData<List<Student>>


    @Delete
    suspend fun deleteStudent(student: Student)



    /*Room with Flow */
  /*  @Query("SELECT * FROM student_table")
    fun getAllStudent(): Flow<List<Student>>*/
}