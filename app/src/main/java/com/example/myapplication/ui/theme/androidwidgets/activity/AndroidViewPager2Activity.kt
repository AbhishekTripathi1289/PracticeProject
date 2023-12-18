package com.example.myapplication.ui.theme.androidwidgets.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityAndroidViewPager2Binding
import com.example.myapplication.databinding.ActivityAndroidWidgetImplementtationBinding
import com.example.myapplication.ui.theme.androidwidgets.PagerSnapAdapter
import com.google.android.material.tabs.TabLayoutMediator

class AndroidViewPager2Activity : AppCompatActivity() {



    private lateinit var binding: ActivityAndroidViewPager2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAndroidViewPager2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewpager.adapter = PagerSnapAdapter(arrayListOf())
        binding.viewpager.offscreenPageLimit = 4

        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = listOf<String>("tab 1","tab 1", "tab 1", "tab 1", "tab 1", "tab 1", "tab 1"
            , "tab 1", "tab 1", "tab 1")[position]
        }.attach()

    }
}