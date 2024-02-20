package com.example.myapplication.ui.theme.androidwidgets.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityAndroidViewPager2Binding
import com.example.myapplication.databinding.TabLayoutTextViewBinding
import com.example.myapplication.ui.theme.androidwidgets.adapter.ViewPagerFragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.roundToInt

class AndroidViewPager2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityAndroidViewPager2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAndroidViewPager2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        /*Recyclerview adapter example */
        //binding.viewpager.adapter = PagerSnapAdapter(arrayListOf())
        /*Fragment StatePagerAdapter Example*/
        binding.viewpager.adapter = ViewPagerFragmentStateAdapter(this)
        binding.viewpager.offscreenPageLimit = 4
        // binding.viewpager.setPageTransformer(getPageTransformer())
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->

            var binding = TabLayoutTextViewBinding.inflate(this.layoutInflater)
            binding.tabTextView.setTextColor(
                ContextCompat.getColor(
                    this@AndroidViewPager2Activity,
                    R.color.color_E94F61_no_change
                )
            )
            binding.tabTextView.text = listOf<String>(
                "tab 1",
                "tabsfasdfsa 1",
                "tab 1",
                "tabdsfadfasf 1",
                "tab 1",
                "tab 1",
                "tab 1",
                "tab 1",
                "tab 1",
                "tab 1"
            )[position]
            tab.setCustomView(binding.root)

        }.attach()

    }

    private fun getPageTransformer(): ViewPager2.PageTransformer {
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(
            MarginPageTransformer(
                dpToPx(
                    this,
                    15.0f
                ).roundToInt()
            )
        )
        compositePageTransformer.addTransformer(object : ViewPager2.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                val r = 1 - Math.abs(position)
                page.scaleY = (0.85 + r.times(0.15)).toFloat()
            }

        })
        return compositePageTransformer
    }

    fun dpToPx(context: Context, dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }
}