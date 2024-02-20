package com.example.myapplication.ui.theme.androidwidgets.adapter

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.ui.theme.androidwidgets.fragment.ViewPagerFragment

class ViewPagerFragmentStateAdapter(var content: FragmentActivity, ): FragmentStateAdapter(content) {

    var  list = arrayListOf("abdsafasc", "abdsafasc" , "abdsafasc", "abdsafasc", "abdsafasc", "abdsafasc","abdsafasc","abdsafasc", "abdsafasc", "abdsafasc" )

    override fun getItemCount(): Int {
       return  list.size
    }

    override fun createFragment(position: Int): Fragment {
     return  ViewPagerFragment.getInstance(list.get(position))
    }
}