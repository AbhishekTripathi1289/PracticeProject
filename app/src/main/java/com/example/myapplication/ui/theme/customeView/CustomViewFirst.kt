package com.example.myapplication.ui.theme.customeView

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.R


class CustomViewFirst @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    init {
        var view = inflate(context, R.layout.custome_view, this)
        setOnClickListener {
            Toast.makeText(context, "Hello toast", Toast.LENGTH_LONG).show()
        }
        var a = view.findViewById<ImageView>(R.id.imageViewFirst)

    }
}