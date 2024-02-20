package com.example.myapplication.ui.theme.androidwidgets.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.AutoScrollLayoutBinding

class ViewPagerFragment: Fragment(R.layout.auto_scroll_layout)
{
    lateinit var bining: AutoScrollLayoutBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      bining = AutoScrollLayoutBinding.bind(view)
        var str = arguments?.getString("key1")
        bining.textView.text = str
    }

    companion object{
        fun getInstance(str: String): Fragment
        {
            var fragment = ViewPagerFragment()
            var bund = Bundle()
            bund.putString("key1", str)
            fragment.arguments = bund
            return fragment
        }

    }
}