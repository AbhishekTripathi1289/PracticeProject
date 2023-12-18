package com.example.myapplication.ui.theme.navigationComponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityNvaigationComponentBinding

class NvaigationComponentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNvaigationComponentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNvaigationComponentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}