package com.example.myapplication.ui.theme.NotesCheezy.viewmodels

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheezycode.notesample.models.UserRequest
import com.cheezycode.notesample.models.UserResponse
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.myapplication.ui.theme.NotesCheezy.repo.UserRepo
import com.example.myapplication.ui.theme.NotesCheezy.utils.Helper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(val userRepo: UserRepo): ViewModel()
{
    private val signUpMutableLiveData: MutableLiveData<DataState<UserResponse>> = MutableLiveData()
     val signUpLiveData
        get() = signUpMutableLiveData

    private val signInMutableLiveDataState: MutableLiveData<DataState<UserResponse>> = MutableLiveData()
     val signInLiveDataState
        get() = signInMutableLiveDataState


    fun signUpUser(userRequest: UserRequest)
    {
        viewModelScope.launch {
            userRepo.signUpUser(userRequest).onEach {
                signUpMutableLiveData.value = it


            }.launchIn(viewModelScope)
        }
    }
    fun signInUser(userRequest: UserRequest)
    {
        viewModelScope.launch {
            userRepo.singInUser(userRequest).onEach {
                signInMutableLiveDataState.value = it
            }.launchIn(viewModelScope)
        }
    }


    fun validateCredentials(emailAddress: String, userName: String, password: String,
                            isLogin: Boolean) : Pair<Boolean, String> {

        var result = Pair(true, "")
        if(TextUtils.isEmpty(emailAddress) || (!isLogin && TextUtils.isEmpty(userName)) || TextUtils.isEmpty(password)){
            result = Pair(false, "Please provide the credentials")
        }
        else if(!Helper.isValidEmail(emailAddress)){
            result = Pair(false, "Email is invalid")
        }
        else if(!TextUtils.isEmpty(password) && password.length <= 5){
            result = Pair(false, "Password length should be greater than 5")
        }
        return result
    }

}