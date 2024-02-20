package com.example.myapplication.ui.theme.androidwidgets.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityGlidePracticeActiivtyBinding

class GlidePracticeActiivty : AppCompatActivity() {

    lateinit var binding: ActivityGlidePracticeActiivtyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlidePracticeActiivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)



        Glide.with(this)
            .load("https://www.howtodoandroid.com/wp-content/uploads/2021/03/glide-poster.webp")
            .centerCrop()
            .placeholder(R.drawable.image)
            .error(R.drawable.image)
            .circleCrop()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(binding.imageView)



        /*  .listener(object: RequestListener<Drawable>{
                   override fun onLoadFailed(
                       e: GlideException?,
                       model: Any?,
                       target: Target<Drawable>?,
                       isFirstResource: Boolean
                   ): Boolean {
                       TODO("Not yet implemented")
                   }

                   override fun onResourceReady(
                       resource: Drawable?,
                       model: Any?,
                       target: Target<Drawable>?,
                       dataSource: DataSource?,
                       isFirstResource: Boolean
                   ): Boolean {
                       TODO("Not yet implemented")
                   }

               })
*/
    }
}