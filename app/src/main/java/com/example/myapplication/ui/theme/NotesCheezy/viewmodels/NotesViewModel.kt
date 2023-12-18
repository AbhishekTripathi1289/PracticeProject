package com.example.myapplication.ui.theme.NotesCheezy.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheezycode.notesample.models.NoteResponse
import com.cheezycode.notesample.models.UserRequest
import com.cheezycode.notesample.models.UserResponse
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.myapplication.ui.theme.NotesCheezy.repo.NotesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(val notesRepo: NotesRepo) : ViewModel() {
    private val allNotesMutableLiveData: MutableLiveData<DataState<List<NoteResponse>>> = MutableLiveData()
    val allNotesLiveData
        get() = allNotesMutableLiveData


    fun signUpUser()
    {
        viewModelScope.launch {
            notesRepo.getAllNotes().onEach {
                allNotesMutableLiveData.value = it
            }.launchIn(viewModelScope)
        }
    }




}