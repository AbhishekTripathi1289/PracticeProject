package com.example.myapplication.ui.theme.androidwidgets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityAndroidWidgetImplementtationBinding

class AndroidWidgetImplementtationActivity : AppCompatActivity() {


    private lateinit var binding: ActivityAndroidWidgetImplementtationBinding
    lateinit var adapter : PagerSnapAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAndroidWidgetImplementtationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpPagerAdapter()
    }

    private fun setUpPagerAdapter() {
        adapter = PagerSnapAdapter(arrayListOf("sadfd", "dsaf"))

        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(CirclePagerIndicatorDecoration())
        pagerSnapHelper.attachToRecyclerView(binding.recyclerView)
        // runnable.run()
    }

    var pagerSnapHelper = PagerSnapHelper()

    val handler = Handler()

    var size = 0
    var count = 0
    val speedScroll = 0


    val runnable = object : Runnable {
        override fun run() {
          /* // loadForward()
            if (count == adapter.list.size - 1) {
                count = 0;
                //binding.recyclerView.scrollToPosition(0);
            } else {
                binding.recyclerView.smoothScrollToPosition(++count);
            }
           // binding.recyclerView.smoothScrollToPosition(++count)
            handler.postDelayed(this, speedScroll.toLong())*/
        }
    }
    val layoutManager = object : LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
        override fun smoothScrollToPosition(recyclerView: RecyclerView?, state: RecyclerView.State?, position: Int) {
            val linearSmoothScroller = object : LinearSmoothScroller(this@AndroidWidgetImplementtationActivity) {
                val SPEED = 5f
                override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                    val speed = SPEED
                    return speed
                }
            }

            linearSmoothScroller.targetPosition = position
            startSmoothScroll(linearSmoothScroller)
        }
    }

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
//            if (dx < 0) {
//                loadBackward()
//            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            when (newState) {
                RecyclerView.SCROLL_STATE_IDLE -> autoScroll()
                RecyclerView.SCROLL_STATE_DRAGGING -> handler.removeCallbacks(runnable)
            }
        }
    }

    fun loadForward() {
        if (adapter.list.size >0 && layoutManager.findLastVisibleItemPosition() == (size - 1)) {
            adapter.list.add(adapter.list.get(0))
            adapter.list.removeAt(0)
            adapter.list[adapter.list.size-1] = "Hello world"
        }
    }
    fun autoScroll() {
        handler.postDelayed(runnable, speedScroll.toLong())
    }
}