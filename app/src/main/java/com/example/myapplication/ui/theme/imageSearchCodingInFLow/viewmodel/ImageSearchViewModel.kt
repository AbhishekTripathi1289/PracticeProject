package com.example.myapplication.ui.theme.imageSearchCodingInFLow.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.myapplication.ui.theme.imageSearchCodingInFLow.repo.ImageSearchRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageSearchViewModel @Inject constructor(val imageSearchRepo: ImageSearchRepo) : ViewModel()
{

    private val currentQuery = MutableLiveData(DEFAULT_VALUE)

    val imageListLiveData = currentQuery.switchMap { queryString ->
        imageSearchRepo.getImageSearchData(queryString).cachedIn(viewModelScope)
    }

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }
    companion object
    {
       const val DEFAULT_VALUE = "cat"
    }
}