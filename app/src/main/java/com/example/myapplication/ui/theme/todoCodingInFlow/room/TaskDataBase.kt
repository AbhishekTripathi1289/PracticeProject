package com.example.myapplication.ui.theme.todoCodingInFlow.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.ui.theme.todoCodingInFlow.data.Task
import com.example.myapplication.ui.theme.todoCodingInFlow.di.TaskModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDataBase: RoomDatabase()
{

    abstract fun getTaskDao():TaskDao
    companion object
    {
       const val DATABASE_NAME = "task_db"
    }




    class Callback @Inject constructor(
        private val database: Provider<TaskDataBase>,
        @TaskModule.ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().getTaskDao()

            applicationScope.launch {
                dao.insertTask(Task(name = "Wash the dishes"))
                dao.insertTask(Task(name = "Do the laundry"))
                dao.insertTask(Task(name = "Buy groceries", important = true))
                dao.insertTask(Task(name = "Prepare food", completed = true))
                dao.insertTask(Task(name = "Call mom"))
                dao.insertTask(Task(name = "Visit grandma", completed = true))
                dao.insertTask(Task(name = "Repair my bike"))
                dao.insertTask(Task(name = "Call Elon Musk"))
            }
        }
    }
}