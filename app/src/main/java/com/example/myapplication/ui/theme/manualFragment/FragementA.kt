package com.example.myapplication.ui.theme.manualFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFragementABinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragementA : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentFragementABinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("FragementA.onCreate")
        arguments.let {
            param1 = it?.getString(ARG_PARAM1)
            param2 = it?.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        println("FragementA.onCreateView")
        binding = FragmentFragementABinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    println("FragementA.onViewCreated")
        binding.button.setOnClickListener{
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.add(R.id.frameLayout, FragementB.getFragmentInstance("dsaf", "sadf"))
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("FragementA.onAttach")
    }

    override fun onPause() {
        super.onPause()
        println("FragementA.onPause")
    }

    override fun onResume() {
        super.onResume()
        println("FragementA.onResume")
    }
    override fun onStop() {
        super.onStop()
        println("FragementA.onStop")
    }

    override fun onStart() {
        super.onStart()
        println("FragementA.onStart")
    }

    override fun onDetach() {
        super.onDetach()
        println("FragementA.onDetach")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("FragementA.onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("FragementA.onDestroyView")
    }



    companion object
    {
        fun getFragmentInstance(arg: String, arg2: String) : FragementA{
            return FragementA().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1 ,arg)
                    putString(ARG_PARAM2, arg2)
                }
            }
        }
    }
}