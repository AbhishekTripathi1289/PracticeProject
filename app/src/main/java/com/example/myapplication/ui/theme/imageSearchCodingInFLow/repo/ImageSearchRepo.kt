package com.example.myapplication.ui.theme.imageSearchCodingInFLow.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.myapplication.ui.theme.imageSearchCodingInFLow.api.UnsplashApi
import com.example.myapplication.ui.theme.imageSearchCodingInFLow.model.UnSplashPagingSource
import javax.inject.Inject

class ImageSearchRepo @Inject constructor(val unsplashApi: UnsplashApi)
{
    fun getImageSearchData(query: String) =
        Pager(config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
            pagingSourceFactory = {
                UnSplashPagingSource(unsplashApi, query )
            }
        ).liveData

}