package com.example.myapplication.ui.theme.manualFragment

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

        arguments.let {
            param1 = it?.getString(ARG_PARAM1)
            param2 = it?.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFragementABinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.button.setOnClickListener{
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frameLayout, FragmentB.getFragmentInstance("dsaf", "sadf"))
                ?.addToBackStack(null)
                ?.commit()
        }
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