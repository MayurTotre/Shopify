package com.example.shopify.view.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.shopify.R
import com.example.shopify.databinding.FragmentSignUpBinding
import com.example.shopify.model.request.CreateUserRequest
import com.example.shopify.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: UserViewModel by viewModels()
    private var base64String: String? = null
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnProfilePic.setOnClickListener {
            openGallery()
        }

        binding.tvClickHere.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val name = binding.etName.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || imageUri == null) {
                Toast.makeText(
                    requireContext(),
                    "Please fill all fields & select an image",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            registerUser()
        }
    }

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                imageUri = it
                base64String = uriToBase64(it)
                binding.btnProfilePic.setImageURI(it)

                Glide.with(requireContext())
                    .load(it)
                    .circleCrop()
                    .into(binding.btnProfilePic)
            }
        }

    private fun openGallery() {
        pickImageLauncher.launch("image/*")
    }

    private fun registerUser() {
        if (imageUri != null) {
            uploadImageToServer(imageUri!!)
        } else {
            Toast.makeText(requireContext(), "Please select an image", Toast.LENGTH_SHORT).show()
        }

        viewModel.uploadImage.observe(viewLifecycleOwner) { result ->
            result.onSuccess { imageResponse ->
                val imageUrl = imageResponse.location
                Log.d("SignUpFragment", "Image uploaded successfully: $imageUrl")
                registerUserWithImage(imageUrl)  // Register user only after image is uploaded
            }.onFailure {
                Log.e("SignUpFragment", "Image upload failed: ${it.message}")
                Toast.makeText(requireContext(), "Image upload failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUserWithImage(imageUrl: String?) {
        val createUserRequest = CreateUserRequest(
            email = binding.etEmail.text.toString().trim(),
            password = binding.etPassword.text.toString().trim(),
            name = binding.etName.text.toString().trim(),
            avatar = imageUrl ?: ""
        )
        viewModel.registerUser(createUserRequest)
    }

    private fun uploadImageToServer(imageUri: Uri) {
        val file = getFileFromUri(imageUri)
        if (file == null) {
            Log.d("UploadImage", "Failed to get file from URI")
            return
        }
        viewModel.uploadImageToServer(file)
    }

    private fun getFileFromUri(imageUri: Uri): File? {
        return try {
            val inputStream = requireContext().contentResolver.openInputStream(imageUri) ?: return null
            val tempFile = File.createTempFile("upload", ".jpg", requireContext().cacheDir)
            tempFile.outputStream().use { output ->
                inputStream.copyTo(output)
            }
            tempFile
        } catch (e: Exception) {
            Log.e("SignUpFragment", "Error getting file from URI: ${e.message}")
            null
        }
    }

    private fun uriToBase64(imageUri: Uri): String? {
        return try {
            val inputStream = activity?.contentResolver?.openInputStream(imageUri)
            val bytes = inputStream?.readBytes()
            inputStream?.close()
            bytes?.let { Base64.encodeToString(it, Base64.DEFAULT) }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
