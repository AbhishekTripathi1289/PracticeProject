package com.example.myapplication.ui.theme.imageSearchCodingInFLow.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.UnsplashPhotoLoadStateFooterBinding

class ImageSearchHeaderFooterAdapter(val callback: () ->Unit): LoadStateAdapter<ImageSearchHeaderFooterAdapter.ImageSearchViewHolder>() {

    override fun onBindViewHolder(holder: ImageSearchViewHolder, loadState: LoadState)
    {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ImageSearchViewHolder
    {
        val binding = UnsplashPhotoLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       return ImageSearchViewHolder(binding)
    }

    inner class ImageSearchViewHolder(val binding: UnsplashPhotoLoadStateFooterBinding):RecyclerView.ViewHolder(binding.root) {


        init {
            binding.buttonRetry.setOnClickListener{
                callback.invoke()
            }
        }
        fun bind(loadState: LoadState)
        {
            binding.run {
                progressBar.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState !is LoadState.Loading
                textViewError.isVisible = loadState !is LoadState.Loading
            }
        }
    }


}