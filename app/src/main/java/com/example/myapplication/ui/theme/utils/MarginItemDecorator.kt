package com.example.myapplication.ui.theme.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecorator(private val marginInDp: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        // Convert dp to pixels
        val margin = (marginInDp * view.resources.displayMetrics.density).toInt()

        // Apply margin to all sides of the item
        outRect.set(0, margin, 0, 0)
    }
}