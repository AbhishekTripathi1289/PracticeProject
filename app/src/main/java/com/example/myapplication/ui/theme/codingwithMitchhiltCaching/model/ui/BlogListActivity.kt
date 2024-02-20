package com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityBlogListBinding
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.Blog
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder

@AndroidEntryPoint
class BlogListActivity : AppCompatActivity()
{
    val viewModel: BlogViewModel  by viewModels()

    private lateinit var binding: ActivityBlogListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBlogListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.setStateEvent(BlogViewModel.MainStateEvent.GetBlogsEvent)
        setObserver()

    }

    private fun setObserver() {
        viewModel.blogState.observe(this) { dataState ->

            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception?.message)
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }

                else -> {}
            }
        }
    }

        private fun displayError(message: String?){
            if(message != null) binding.text.text = message else binding.text.text = "Unknown error."
        }

        private fun appendBlogTitles(blogs: List<Blog>){
            val sb = StringBuilder()
            for(blog in blogs){
                sb.append(blog.title + "\n")
            }
            binding.text.text = sb.toString()
        }

        private fun displayProgressBar(isDisplayed: Boolean){
            binding.progressBar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
        }
}