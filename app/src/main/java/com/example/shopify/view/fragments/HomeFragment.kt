package com.example.shopify.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopify.R
import com.example.shopify.adapter.ProductCategoriesAdapter
import com.example.shopify.adapter.ProductsAdapter
import com.example.shopify.databinding.FragmentHomeBinding
import com.example.shopify.databinding.FragmentLoginBinding
import com.example.shopify.databinding.FragmentWishListBinding
import com.example.shopify.interfaces.OnClickDoAction
import com.example.shopify.interfaces.OnClickGetDetails
import com.example.shopify.model.response.ProductsResponseItem
import com.example.shopify.viewmodel.CategoriesViewModel
import com.example.shopify.viewmodel.WishListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnClickGetDetails, OnClickDoAction {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: CategoriesViewModel by viewModels()
    private val wishListViewModel: WishListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.categories()
        viewModel.categories.observe(viewLifecycleOwner) { result ->
            result.onSuccess { categoriesResponse ->
                val data = categoriesResponse
                val categoriesAdapter = ProductCategoriesAdapter(data, this)
                binding.rvcategories.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rvcategories.adapter = categoriesAdapter
                Log.d("Categories", "${categoriesResponse}")
            }
            result.onFailure {
                Log.d("Categories", "${it.message}")
            }
        }


        viewModel.products.observe(viewLifecycleOwner) { result ->
            result.onSuccess { productResponse ->
                val data = productResponse
                val productsAdapter = ProductsAdapter(productResponse, this)
                binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rvProducts.adapter = productsAdapter
                Log.d("Products", "${productResponse}")
            }
            result.onFailure {
                Log.d("Products", "${it.message}")
            }
        }

    }

    override fun getProductById(id: Int) {
        viewModel.products(id)
    }

    override fun onCliCkDoAction(product: ProductsResponseItem) {
        wishListViewModel.addToWishList(product)
    }

}