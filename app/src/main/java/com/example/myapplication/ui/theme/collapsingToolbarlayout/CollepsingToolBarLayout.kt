package com.example.myapplication.ui.theme.collapsingToolbarlayout

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityCollepsingToolBarLayoutBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener


class CollepsingToolBarLayout : AppCompatActivity() {
    lateinit var binding: ActivityCollepsingToolBarLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollepsingToolBarLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)


        binding.appBarLayout.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    binding.collapsBarLayout.title = "Hello world"
                    isShow = true
                } else if (isShow) {
                    binding.collapsBarLayout.title = " " //careful there should a space between double quote otherwise it wont work
                    isShow = false
                }
            }
        })
    }
}