package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.cheezycode.notesample.models.UserRequest
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.myapplication.databinding.FragmentSingUpBinding
import com.example.myapplication.ui.theme.NotesCheezy.utils.Helper.Companion.hideKeyboard
import com.example.myapplication.ui.theme.NotesCheezy.utils.TokenManager
import com.example.myapplication.ui.theme.NotesCheezy.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SingUpFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentSingUpBinding

    @Inject
    lateinit var tokenManager: TokenManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSingUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(tokenManager.getToken() != null)
        {
            val action = SignInFragmentDirections.actionSignInFragmentToMainFragment()
            findNavController().navigate(action)
        }
        addobserver()
        binding.btnLogin.setOnClickListener{
            var action = SingUpFragmentDirections.actionSingUpFragmentToSignInFragment2()
            findNavController().navigate(action)
        }

        binding.btnSignUp.setOnClickListener{
            hideKeyboard(it)
            val validationResult = validateUserInput()
            if (validationResult.first) {
                val userRequest = getUserRequest()
                userViewModel.signUpUser(userRequest)
            } else {
                showValidationErrors(validationResult.second)
            }
        }
    }
    private fun addobserver() {
        userViewModel.signUpLiveData.observe(viewLifecycleOwner){ userState->
            when(userState)
            {
                is DataState.Success ->
                {
                    tokenManager.saveToken(userState.data.token)
                    binding.progressBar.visibility = View.GONE
                    val action = SingUpFragmentDirections.actionSingUpFragmentToMainFragment()
                    findNavController().navigate(action)
                }
                is DataState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.txtError.text = userState.message
                }
                DataState.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun validateUserInput(): Pair<Boolean, String> {
        val emailAddress = binding.txtEmail.text.toString()
        val userName = binding.txtUsername.text.toString()
        val password = binding.txtPassword.text.toString()
        return userViewModel.validateCredentials(emailAddress, userName, password, false)
    }
    private fun showValidationErrors(error: String) {
        binding.txtError.text = String.format(resources.getString(R.string.txt_error_message, error))
    }
    private fun getUserRequest(): UserRequest {
        return binding.run {
            UserRequest(
                txtEmail.text.toString(),
                txtPassword.text.toString(),
                txtUsername.text.toString()
            )
        }
    }

}