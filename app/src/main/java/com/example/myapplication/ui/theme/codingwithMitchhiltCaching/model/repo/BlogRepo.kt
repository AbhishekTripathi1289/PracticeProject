package com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.repo

import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.Blog
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.retrofit.BlogRetrofit
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.retrofit.NetworkMapper
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.room.BlogDao
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.room.CacheMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlogRepo @Inject constructor(
    private val retrofit: BlogRetrofit,
    private val blogDao: BlogDao,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
)
{
    suspend fun getBlogs(): Flow<DataState<List<Blog>>>
    {
       return  flow {

           emit(DataState.Loading)
           delay(1000)
           try {
           val networkBlogs = retrofit.get()
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