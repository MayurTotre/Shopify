package com.example.shopify.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.shopify.R
import com.example.shopify.databinding.FragmentProductDetailsBinding
import com.example.shopify.model.response.AddtoCartProductResponse
import com.example.shopify.model.response.Category
import com.example.shopify.model.response.ProductsResponseItem
import com.example.shopify.viewmodel.AddToCartViewModel
import com.example.shopify.viewmodel.DisplayProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private val viewModel: DisplayProductsViewModel by viewModels()
    private val addToCartViewModel: AddToCartViewModel by viewModels()
    private lateinit var addtoCartProductResponse: AddtoCartProductResponse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments?.getInt("productId")
        viewModel.getProductDetails(productId!!)

        viewModel.productDetailsData.observe(viewLifecycleOwner) { result ->
            result.onSuccess { response ->
                    addtoCartProductResponse = AddtoCartProductResponse(
                    id = response.id,
                    category = response.category,
                    creationAt = response.creationAt,
                    description = response.description,
                    images = response.images,
                    price = response.price,
                    slug = response.slug,
                    title = response.title,
                    updatedAt = response.updatedAt,
                    quantity = 1
                )
                binding.tvProductDescription.text = response.description
                binding.tvPrice.text = response.price.toString()

                Glide
                    .with(binding.ivProductImage.context)
                    .load(response.images[0])
                    .placeholder(R.drawable.shopify_logo)
                    .into(binding.ivProductImage)

                if(response.category.name.lowercase() == "clothes"){
                    binding.tvSize.visibility = View.VISIBLE
                    binding.rgSize.visibility = View.VISIBLE
                }else{
                    binding.tvSize.visibility = View.GONE
                    binding.rgSize.visibility = View.GONE
                }

            }
            result.onFailure {
                Log.d("ProductDetailsResponse", it.toString())
            }
        }

        binding.btnAddToCart.setOnClickListener {
            Log.d("addToCartResponse", "$addtoCartProductResponse")

            lifecycleScope.launch {
                if (addToCartViewModel.isItemAvailableInCart(addtoCartProductResponse.id) == false){
                    addToCartViewModel.addItemToCart(addtoCartProductResponse)
                    binding.btnAddToCart.setText("Remove From Cart")
                }else{
                    addToCartViewModel.deleteProductFromCart(addtoCartProductResponse.id)
                    binding.btnAddToCart.setText("Add To Cart")
                }

            }
        }
    }
}