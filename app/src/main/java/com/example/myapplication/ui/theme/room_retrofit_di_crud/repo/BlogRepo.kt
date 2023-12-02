package com.example.myapplication.ui.theme.room_retrofit_di_crud.repo

import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.Blog
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.retrofit.NetworkMapper
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.room.BlogDao
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.room.CacheMapper
import com.example.myapplication.ui.theme.room_retrofit_di_crud.retrofit.BlogApiSecond
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BlogRepo @Inject constructor(var retrofit: BlogApiSecond,
                                   private val blogDao: BlogDao,
                                   private val cacheMapper: CacheMapper,
                                   private val networkMapper: NetworkMapper
) {


    suspend fun getAllBlogs(): Flow<DataState<List<Blog>>>
    {

        return flow {
            emit(DataState.Loading)
            delay(2000)

            try {
                val networkBlogs = retrofit.getBlogs()
                val mapperBlogs = networkMapper.mapFromEntityList(networkBlogs)
                mapperBlogs.forEach{
                    blogDao.insert(cacheMapper.mapToEntity(it))
                }
                val cachedBlogs = blogDao.getBlogs()
                emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
            }
            catch (exception : Exception)
            {
                emit(DataState.Error(exception))
            }
        }
    }
}