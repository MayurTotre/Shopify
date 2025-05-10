package com.example.shopify.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.shopify.R
import com.example.shopify.databinding.FragmentLoginBinding
import com.example.shopify.databinding.FragmentSignUpBinding
import com.example.shopify.model.request.LoginUserRequest
import com.example.shopify.utils.SharedPreferencesHelper
import com.example.shopify.view.activities.HomeActivity
import com.example.shopify.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: UserViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        sharedPreferences = SharedPreferencesHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            val loginUserRequest = LoginUserRequest(email, password)
            viewModel.loginUser(loginUserRequest)

            viewModel.loginState.observe(viewLifecycleOwner) { result ->
                result.onSuccess {
                    sharedPreferences.saveData(it.access_token).toString()
                    val access_token = sharedPreferences.getData()
                    Log.d("access_token", "Access Token: '$access_token")

                    Intent(requireContext(), HomeActivity::class.java).also {
                        startActivity(it)
                        requireActivity().finish()
                    }
                    Log.d("LoginFragment", "User Logged In Successfully ${it}")
                }
                result.onFailure {
                    Log.d("LoginFragment", "User Logged In Successfully ${it.message}")
                }
            }
        }
    }
}