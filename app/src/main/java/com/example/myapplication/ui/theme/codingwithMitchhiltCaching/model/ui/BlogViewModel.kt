package com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.Blog
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.repo.BlogRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class BlogViewModel @Inject constructor(private val mainRepository: BlogRepo): ViewModel()
{
    private val _blogState : MutableLiveData<DataState<List<Blog>>> = MutableLiveData()
    val blogState : LiveData<DataState<List<Blog>>>
        get() = _blogState


    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("MainActivity", "Coroutine Exception: ${throwable.message}", throwable)
    }

    fun setStateEvent(mainStateEvent: MainStateEvent){

        viewModelScope.launch(Dispatchers.IO + exceptionHandler ) {

            when(mainStateEvent)
            {
                MainStateEvent.GetBlogsEvent -> {
                    mainRepository.getBlogs().onEach {
                       _blogState.value = it
                    }.launchIn(viewModelScope)
                }
                MainStateEvent.None ->{

                }
            }
        }

    }

    sealed class MainStateEvent{

        object GetBlogsEvent: MainStateEvent()

        object None: MainStateEvent()
    }

}