package com.example.myapplication.ui.theme.navigationComponent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("LoginFragment.onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        println("LoginFragment.onCreateView")
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("LoginFragment.onViewCreated")
        super.onViewCreated(view, savedInstanceState)
       /* binding.buttonConfirm.setOnClickListener{
           var action =
               com.example.myapplication.LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(
                   binding.editTextUsername.text.toString(),
                   binding.editTextPassword.text.toString()
               )
            findNavController().navigate(action)*/
     //   }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("LoginFragment.onAttach")
    }

    override fun onStart() {
        super.onStart()
        println("LoginFragment.onStart")
    }

    override fun onResume() {
        super.onResume()
        println("LoginFragment.onResume")
    }


    override fun onPause() {
        super.onPause()
        println("LoginFragment.onPause")
    }

    override fun onStop() {
        super.onStop()
        println("LoginFragment.onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("LoginFragment.onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("LoginFragment.onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        println("LoginFragment.onDetach")
    }

    companion object  {
        val TAG = "LOGINFragment"
    }
}