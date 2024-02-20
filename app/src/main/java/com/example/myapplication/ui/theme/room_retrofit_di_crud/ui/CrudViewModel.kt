package com.example.myapplication.ui.theme.room_retrofit_di_crud.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.Blog
import com.example.myapplication.ui.theme.room_retrofit_di_crud.model.Student
import com.example.myapplication.ui.theme.room_retrofit_di_crud.repo.BlogRepo
import com.example.myapplication.ui.theme.room_retrofit_di_crud.repo.CrudRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CrudViewModel @Inject constructor(private var repo:CrudRepo, private var blogRepo: BlogRepo) :ViewModel()
{

    lateinit var studentList : LiveData<List<Student>>

    /*private val _studentList : MutableLiveData<DataState<List<Student>>> = MutableLiveData()
    val studentList
        get() = _studentList*/

    private val _blogsList : MutableLiveData<DataState<List<Blog>>> = MutableLiveData()
    val blogsList
        get() = _blogsList

   /* fun getAllStudent(){
        viewModelScope.launch{
            repo.getAllStudent().onEach {
                _studentList.value = it
            }.launchIn(viewModelScope)
        }
    }*/

    fun getAllStudent(){
        viewModelScope.launch{
            studentList =  repo.getAllStudent()
        }
    }
    fun getAllBlogList(){
        viewModelScope.launch{
            blogRepo.getAllBlogs().onEach {
                _blogsList.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun insertStudent(student: Student)
    {
        viewModelScope.launch {
            repo.insertStudent(student)
        }
    }

    fun deleteStudent(student: Student)
    {
        viewModelScope.launch {
            repo.deleteStudent(student)
        }
    }
}