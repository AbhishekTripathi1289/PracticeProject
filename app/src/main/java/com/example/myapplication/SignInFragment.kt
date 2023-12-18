package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cheezycode.notesample.models.UserRequest
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.myapplication.databinding.FragmentSignInBinding
import com.example.myapplication.ui.theme.NotesCheezy.utils.Helper
import com.example.myapplication.ui.theme.NotesCheezy.utils.TokenManager
import com.example.myapplication.ui.theme.NotesCheezy.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class SignInFragment : Fragment() {


    private lateinit var binding: FragmentSignInBinding

    private val userViewModel: UserViewModel by activityViewModels()

    @Inject
    lateinit var tokenManager: TokenManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addobserver()

        binding.btnLogin.setOnClickListener{
            Helper.hideKeyboard(it)
            val validationResult = validateUserInput()
            if (validationResult.first) {
                val userRequest = getUserRequest()
                userViewModel.signInUser(userRequest)
            } else {
                showValidationErrors(validationResult.second)
            }
        }
        binding.btnSignUp.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun getUserRequest(): UserRequest {
        return binding.run {
            UserRequest(
                txtEmail.text.toString(),
                txtPassword.text.toString(),
                ""
            )
        }
    }

    private fun showValidationErrors(error: String) {
        binding.txtError.text = String.format(resources.getString(R.string.txt_error_message, error))
    }

    private fun validateUserInput(): Pair<Boolean, String> {
        val emailAddress = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()
        return userViewModel.validateCredentials(emailAddress, "" , password, true)
    }

    private fun addobserver() {
       userViewModel.signInLiveDataState.observe(requireActivity()){ userState->
           when(userState)
           {
               is DataState.Success ->
               {
                   tokenManager.saveToken(userState.data.token)
                   binding.progressBar.visibility = View.GONE
                   val action = SignInFragmentDirections.actionSignInFragmentToMainFragment()
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


}