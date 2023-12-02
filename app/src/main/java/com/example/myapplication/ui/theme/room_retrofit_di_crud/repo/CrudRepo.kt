package com.example.myapplication.ui.theme.room_retrofit_di_crud.repo

import androidx.lifecycle.LiveData
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.myapplication.ui.theme.room_retrofit_di_crud.db.CrudDao
import com.example.myapplication.ui.theme.room_retrofit_di_crud.model.Student
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CrudRepo @Inject constructor(private var crudDao: CrudDao) {

    suspend fun insertStudent(student: Student)
        {
            crudDao.upsert(student)
        }
 /*   suspend fun getAllStudent() : Flow<DataState<List<Student>>>
    {
       return flow{
           emit(DataState.Loading)
            try
            {
                crudDao.getAllStudent().collect {
                    emit(DataState.Success(it))
                }
            }
            catch (exception: Exception)
            {
            emit(DataState.Error(exception))
            }
        }
    }
*/

     fun getAllStudent() : LiveData<List<Student>> {
        return  crudDao.getAllStudent()
    }


    suspend fun deleteStudent(student: Student)
    {
        crudDao.deleteStudent(student)
    }
}