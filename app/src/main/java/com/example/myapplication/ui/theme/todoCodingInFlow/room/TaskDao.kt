package com.example.myapplication.ui.theme.todoCodingInFlow.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.ui.theme.todoCodingInFlow.data.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)


    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM task_table where name LIKE '%' || :str || '%' ORDER BY important DESC")
    fun getTask(str:String): Flow<List<Task>>

}