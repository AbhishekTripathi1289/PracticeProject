package com.example.myapplication.ui.theme.cheezyCode

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@InstallIn(SingletonComponent::class)
@Module
class UserModule {


    /*This is example of bind annotation in this case you need to create class and method both abstrect*/
   /* @Binds
    abstract fun bindRepo(fireaseRepo: MyLocalRepo): MyRepositary*/



    @Provides
    @Named("str1")
    fun provideStrFirst() =  "This is string first"


    @Provides
    @Named("str2")
    fun provideStrSecond() =  "This is string Second"

    @Provides
    @Named("MyLocal")
    fun provideLocalRepo(localRepo: MyLocalRepo): MyRepositary
    {
        return localRepo
    }


    @Provides
    @Named("MyFirease")
    fun provideFirebaseRepo(localRepo: FireBaseRepo): MyRepositary
    {
        return localRepo
    }
}