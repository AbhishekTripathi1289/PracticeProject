package com.example.myapplication.ui.theme.androidwidgets.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityPracticeBinding
import com.example.myapplication.ui.theme.androidwidgets.dialog.MyFirstDialog

class PracticeActivity : AppCompatActivity() {

    lateinit var binding: ActivityPracticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            MyFirstDialog(this)
        }
    }

    override fun onBackPressed() {
        return;
    }
}