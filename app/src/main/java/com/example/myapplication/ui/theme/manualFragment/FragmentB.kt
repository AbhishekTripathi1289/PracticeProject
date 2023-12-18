package com.example.myapplication.ui.theme.manualFragment

import android.content.Context
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

        println("FragmentB.onCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("FragmentB.onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        println("FragmentB.onAttach")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("FragmentB.onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        println("FragmentB.onStart")
    }

    override fun onResume() {
        super.onResume()
        println("FragmentB.onResume")
    }

    override fun onPause() {
        super.onPause()
        println("FragmentB.onPause")
    }

    override fun onStop() {
        super.onStop()
        println("FragmentB.onStop")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        println("FragmentB.onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        println("FragmentB.onDetach")
    }
    override fun onDestroy() {
        super.onDestroy()
        println("FragmentB.onDestroy")
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