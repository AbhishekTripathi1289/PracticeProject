package com.example.myapplication.ui.theme.NotesCheezy.repo

import com.cheezycode.notesample.models.NoteResponse
import com.cheezycode.notesample.models.UserRequest
import com.cheezycode.notesample.models.UserResponse
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.myapplication.ui.theme.NotesCheezy.api.NotesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import javax.inject.Inject

class NotesRepo @Inject constructor(val notesApi: NotesApi) {

    suspend fun getAllNotes() : Flow<DataState<List<NoteResponse>>> {

        return flow {
            try {
                emit(DataState.Loading)
                val response = notesApi.getAllNotes()

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