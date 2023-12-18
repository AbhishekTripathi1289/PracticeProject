package com.example.myapplication.ui.theme.androidwidgets.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.myapplication.databinding.AutoScrollLayoutBinding
import com.example.myapplication.databinding.LayoutImageBinding

class ImageViewPagerWithCirculeTabLayoutAdapter() : RecyclerView.Adapter<ImageViewPagerWithCirculeTabLayoutAdapter.ViewHolderClass>()
{

    inner class ViewHolderClass(var binding : LayoutImageBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        var binding =  LayoutImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderClass(binding)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {

    }
}