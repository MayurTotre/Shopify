package com.example.shopify.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopify.R
import com.example.shopify.adapter.WishListAdapter
import com.example.shopify.databinding.FragmentWishListBinding
import com.example.shopify.viewmodel.WishListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishListFragment : Fragment() {
    private lateinit var binding: FragmentWishListBinding
    private val viewModel: WishListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllProducts()
        viewModel.productsResponseItem.observe(viewLifecycleOwner, Observer { data ->
            val adapter = WishListAdapter(data)
            binding.rvWishList.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rvWishList.adapter = adapter
        })
    }
}