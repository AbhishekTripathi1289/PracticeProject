package com.example.myapplication.ui.theme.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingwithmitch.daggerhiltplayground.util.DataState
import kotlinx.coroutines.*
abstract class ViewModelBase : ViewModel() {

    var ioDispatcher = Dispatchers.IO
    var job = Job()
    var supervisorJob = SupervisorJob()
    var globalErrorThrowable = MutableLiveData<Throwable>()
    var handler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        globalErrorThrowable.postValue(exception)
    }


    var uiScopeWithGlobalErrorHandler = CoroutineScope(Dispatchers.Main + job + handler)
        get(){
            if(!field.isActive)
            {
                var job = Job()
                field =  CoroutineScope(Dispatchers.Main + job + handler)
            }
            return field

        }


    var uiSupervisorScope = CoroutineScope(Dispatchers.Main + supervisorJob + handler)
        get(){
            if(!field.isActive)
            {
                var job = Job()
                field =  CoroutineScope(Dispatchers.Main + supervisorJob + handler)
            }
            return field

        }

    //use this scope whenever you want to handle a global level error eg displaying error layout on the whole screen
    var ioScopeWithGlobalErrorHandler = CoroutineScope(Dispatchers.IO + job + handler)
        get(){
            if(!field.isActive)
            {
                job = Job()
                field =  CoroutineScope(Dispatchers.IO + job + handler)
            }
            return field
        }


    //use this scope whenever you want to handle the errors using individual try catch blocks
    var ioScope = CoroutineScope(Dispatchers.IO + job )
        get(){
            if(!field.isActive)
            {
                var job = Job()
                field =  CoroutineScope(Dispatchers.IO + job)
            }
            return field
        }


    var ioSupervisorScope = CoroutineScope(Dispatchers.IO + supervisorJob+ handler)
        get(){
            if(!field.isActive)
            {
                var job = Job()
                field =  CoroutineScope(Dispatchers.IO + supervisorJob + handler)
            }
            return field
        }


    public fun  getIoScopeWithErrorHandling(mutableLiveData: Any) : CoroutineScope {
        mutableLiveData as  MutableLiveData<DataState<Any>>
        var job = Job()
        var errorHandler = CoroutineExceptionHandler { _, exception ->
            exception.printStackTrace()
            mutableLiveData.postValue(DataState.Error(exception as Exception))

        }

        var ioScope = CoroutineScope(ioDispatcher + job + errorHandler )

        return ioScope

            }

    public fun  getIoScopeWithoutErrorHandling() : CoroutineScope {
        var job = Job()
        var ioScope = CoroutineScope(ioDispatcher + job  )

        return ioScope

    }



    public fun  getIoScopeWithErrorHandlingForMultiple(mutableLiveData: Any) : CoroutineScope {
        mutableLiveData as  MutableLiveData<DataState<Any>>
        var job = SupervisorJob()
        var errorHandler = CoroutineExceptionHandler { _, exception ->
            exception.printStackTrace()
            mutableLiveData.postValue(DataState.Error(exception as Exception))

        }

        var ioScope = CoroutineScope(ioDispatcher + job + errorHandler )

        return ioScope

    }

    override fun onCleared() {
        super.onCleared()

    }



}