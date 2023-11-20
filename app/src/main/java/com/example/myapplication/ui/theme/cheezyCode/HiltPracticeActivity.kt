package com.example.myapplication.ui.theme.cheezyCode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class HiltPracticeActivity : AppCompatActivity() {

    @Inject
    @Named("MyFirease")
    lateinit var myRepositary: MyRepositary


    @Inject
    @Named("str1")
    lateinit var strFirst : String


    @Inject
    @Named("str2")
    lateinit var strSecond : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilt_practice)

        myRepositary.showData(strFirst + strSecond)
    }
}