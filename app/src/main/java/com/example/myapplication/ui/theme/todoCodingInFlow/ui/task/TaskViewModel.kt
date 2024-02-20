package com.example.myapplication.ui.theme.todoCodingInFlow.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.myapplication.ui.theme.todoCodingInFlow.room.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(val taskDao: TaskDao): ViewModel()
{
    val searchQuery: MutableStateFlow<String> = MutableStateFlow("")

    private val taskFlow = searchQuery.flatMapLatest {
        taskDao.getTask(it)
    }
    val todoTask = taskFlow.asLiveData()

}