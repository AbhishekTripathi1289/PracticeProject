package com.example.myapplication.ui.theme.manualFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentB : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    companion object {
        fun getFragmentInstance(arg1: String , arg2: String): FragmentB
        {
            return FragmentB().apply {
                Bundle().apply {
                    putString(ARG_PARAM1, arg1)
                    putString(ARG_PARAM2, arg2)
                }
            }
        }
    }
}