package com.example.myapplication.ui.theme.cheezyCode

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidApp
class MyApplication: Application()
{
    @Inject
    @Named("MyLocal")
    lateinit var myRepo: MyRepositary
    override fun onCreate() {
        super.onCreate()
        myRepo.showData("abhishek tripahti")
    }

}