package com.example.myapplication.ui.theme.NotesCheezy.repo

import android.service.autofill.Dataset
import com.cheezycode.notesample.models.UserRequest
import com.cheezycode.notesample.models.UserResponse
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.myapplication.ui.theme.NotesCheezy.api.UserApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import javax.inject.Inject

class UserRepo @Inject constructor(val userApi: UserApi)
{

    suspend fun singInUser(userRequest: UserRequest) :Flow<DataState<UserResponse>>{

        return flow {

            try {
                emit(DataState.Loading)
                val response = userApi.singnIn(userRequest)

                if(response.isSuccessful  && response.body() != null)
                {
                    emit(DataState.Success(response.body()!!))
                }
                else if(response.errorBody() != null)
                {
                    val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                    emit(DataState.Error(message = errorObj.getString("message")))
                }
                else{
                    emit(DataState.Error(message = "Something Went Wrong"))
                }
            }
            catch (e: Exception)
            {
              emit(DataState.Error(message = e.message))
            }
        }
    }
    suspend fun signUpUser(userRequest: UserRequest) : Flow<DataState<UserResponse>> {

        return flow {

            try {
                emit(DataState.Loading)
                val response = userApi.singnUp(userRequest)

                if(response.isSuccessful  && response.body() != null)
                {
                    emit(DataState.Success(response.body()!!))
                }
                else if(response.errorBody() != null)
                {
                    val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                    emit(DataState.Error(message = errorObj.getString("message")))
                }
                else{
                    emit(DataState.Error(message = "Something Went Wrong"))
                }
            }
            catch (e: Exception)
            {
                emit(DataState.Error(message = e.message))
            }
        }
    }

}