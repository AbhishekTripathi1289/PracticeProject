package com.example.myapplication.ui.theme.imageSearchCodingInFLow.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemUnsplashPhotoBinding
import com.example.myapplication.ui.theme.imageSearchCodingInFLow.model.UnsplashPhoto

class ImagePagerAdapter (val callback : (UnsplashPhoto) -> Unit) :
    PagingDataAdapter<UnsplashPhoto, ImagePagerAdapter.ImagePagerViewHolder>(ImageSearchComparTor) {


    override fun onBindViewHolder(holder: ImagePagerViewHolder, position: Int) {

        val currentItem = getItem(position)
        currentItem?.let { holder.bind(it) }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePagerViewHolder {
        val bining =
            ItemUnsplashPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagePagerViewHolder(bining)
    }


    companion object {

        private val ImageSearchComparTor = object : DiffUtil.ItemCallback<UnsplashPhoto>() {
            override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: UnsplashPhoto,
                newItem: UnsplashPhoto
            ) =
                oldItem == newItem
        }

    }


    inner class ImagePagerViewHolder(val binding: ItemUnsplashPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener{
                val position = bindingAdapterPosition
                val item  = getItem(position)
                if(item != null)
                {
                    callback.invoke(item)
                }
            }
        }
        fun bind(unSplashPhoto: UnsplashPhoto) {
            binding.apply {

                Glide.with(itemView)
                    .load(unSplashPhoto.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.image)
                    .into(imageView)


                textViewUserName.text = unSplashPhoto.user.username
            }

        }
    }


}