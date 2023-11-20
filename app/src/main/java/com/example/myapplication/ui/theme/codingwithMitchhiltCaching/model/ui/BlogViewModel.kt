package com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.Blog
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.repo.BlogRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    fun setStateEvent(mainStateEvent: MainStateEvent){

        viewModelScope.launch {

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