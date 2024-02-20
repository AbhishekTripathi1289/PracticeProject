package com.example.myapplication.ui.theme.androidwidgets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.AutoScrollLayoutBinding

class AutoScrollAdapter(var list: ArrayList<String>) : RecyclerView.Adapter<AutoScrollAdapter.ViewHolderClass>()
{
    init {
        list = arrayListOf("abdsafasc", "abdsafasc" , "abdsafasc", "abdsafasc", "abdsafasc", "abdsafasc","abdsafasc","abdsafasc", "abdsafasc", "abdsafasc" )
    }
    inner class ViewHolderClass(var binding : AutoScrollLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(get: String) {
                binding.textView.text = get
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        var binding =  AutoScrollLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderClass(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        holder.bind(list.get(position))
    }
}