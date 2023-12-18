package com.example.myapplication.ui.theme.androidwidgets.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityViewPagerWithCirculerTabBinding
import com.example.myapplication.ui.theme.androidwidgets.activity.mix.HorizontalMarginItemDecoration
import com.example.myapplication.ui.theme.androidwidgets.activity.mix.StoriesProgressView
import com.example.myapplication.ui.theme.androidwidgets.adapter.ImageViewPagerWithCirculeTabLayoutAdapter
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.Math.abs


class ViewPagerWithCirculerTab : AppCompatActivity() {
    private lateinit var binding: ActivityViewPagerWithCirculerTabBinding
    private var curSelectedPosition = 0
    private val totalTime: Long = 6*1000
    private var listSize :Int = 10
    private var shouldUpdateTopMargin: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewPagerWithCirculerTabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPager.adapter = ImageViewPagerWithCirculeTabLayoutAdapter()

        // You need to retain one page on each side so that the next and previous items are visible
        binding.viewPager.offscreenPageLimit = 1

        // Add a PageTransformer that translates the next and previous items horizontally
        // towards the center of the screen, which makes them visible
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            // Next line scales the item's height. You can remove it if you don't want this effect
            //page.scaleY = 1 - (0.25f * abs(position))
            // If you want a fading effect uncomment the next line:
            // page.alpha = 0.25f + (1 - abs(position))
        }
        binding.viewPager.setPageTransformer(pageTransformer)

            // The ItemDecoration gives the current (centered) item horizontal margin so that
            // it doesn't occupy the whole screen width. Without it the items overlap
        val itemDecoration = HorizontalMarginItemDecoration(
            this,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.viewPager.addItemDecoration(itemDecoration)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setCustomView(com.example.myapplication.R.layout.textview)
        }.attach()


        binding.viewPager.registerOnPageChangeCallback( object : ViewPager2.OnPageChangeCallback()
        {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                curSelectedPosition = position
            }
        })
        with(binding)
        {
            var storiesListener = object : StoriesProgressView.StoriesListener {
                override fun onNext() {
                    moveToNextCard()
                }

                override fun onNextStarted() {
                }

                override fun onPrev() {
                    moveToPrevCard()
                }

                override fun onComplete() {
                    moveToNextCard()
                }

                override fun playStory() {
                    indicators.playAnimation(curSelectedPosition)
                }

                override fun pauseStory() {
                    indicators.stopAnimation(curSelectedPosition)
                }
            }

            indicators.setStoriesListener(storiesListener)
            indicators.setStoriesCountWithDuration(listSize, totalTime)
            indicators.startStories()


            indicators.playAnimation(0, true)


        }


    }

    private fun moveToPrevCard() {
        val prevPos = (curSelectedPosition - 1 + listSize) % listSize
        binding.viewPager.setCurrentItem(prevPos)
    }

    private fun moveToNextCard() {

        val nextPos = (curSelectedPosition + 1) % listSize
        binding.viewPager.setCurrentItem(nextPos)
    }
}