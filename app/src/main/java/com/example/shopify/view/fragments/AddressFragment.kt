package com.example.shopify.view.fragments

import android.location.Address
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.shopify.R
import com.example.shopify.databinding.FragmentAddressBinding
import com.example.shopify.model.response.AddressResponse
import com.example.shopify.viewmodel.AddressViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressFragment : Fragment() {
    private lateinit var binding: FragmentAddressBinding
    private val addressViewModel: AddressViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)






        binding.btnSaveAddress.setOnClickListener {
            val address = binding.etAddress.text.toString()
            val city = binding.etCityName.text.toString()
            val state = binding.etStateName.text.toString()
            val zipCode = binding.etZipCode.text.toString().toIntOrNull() ?: 0
            val country = binding.etCountry.text.toString()

            val addressResponse = AddressResponse(
                id = 0,
                address = address,
                city = city,
                state = state,
                zipCode = zipCode,
                country = country
            )
            addressViewModel.addAddress(addressResponse)
            Log.d("DEMO_Address", addressResponse.toString())

            binding.etAddress.setText("")
            binding.etCityName.setText("")
            binding.etStateName.setText("")
            binding.etZipCode.setText("")
            binding.etCountry.setText("")
        }


    }
}