package com.example.shopify.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopify.R
import com.example.shopify.adapter.CartItemAdapter
import com.example.shopify.databinding.FragmentDisplayCartItemsBinding
import com.example.shopify.viewmodel.AddToCartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplayCartItemsFragment : Fragment() {
    private lateinit var binding: FragmentDisplayCartItemsBinding
    private val addToCartViewModel: AddToCartViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDisplayCartItemsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addToCartViewModel.getAllItemsFromCart()
        addToCartViewModel.addToCartData.observe(viewLifecycleOwner){result ->
            Log.d("addToCartData", "$result")
            binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
            binding.rvCart.adapter = CartItemAdapter(result)
        }

        binding.btnCheckOut.setOnClickListener {
            findNavController().navigate(R.id.action_displayCartItemsFragment_to_addressListFragment)
        }
    }

}