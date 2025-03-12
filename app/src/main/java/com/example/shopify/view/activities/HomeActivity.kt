package com.example.shopify.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopify.R
import com.example.shopify.adapter.ProductCategoriesAdapter
import com.example.shopify.adapter.ProductsAdapter
import com.example.shopify.databinding.ActivityHomeBinding
import com.example.shopify.interfaces.OnClickGetDetails
import com.example.shopify.model.response.ProductsResponse
import com.example.shopify.viewmodel.CategoriesViewModel
import com.example.shopify.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    findNavController(R.id.nav_home_fragment).navigate(R.id.homeFragment)
                    true
                }
                R.id.fav -> {
                    findNavController(R.id.nav_home_fragment).navigate(R.id.wishListFragment)
                    true
                }
                R.id.cart -> {
                    findNavController(R.id.nav_home_fragment).navigate(R.id.wishListFragment)
                    true
                }
                R.id.box -> {
                    findNavController(R.id.nav_home_fragment).navigate(R.id.wishListFragment)
                    true
                }
                R.id.profile -> {
                    findNavController(R.id.nav_home_fragment).navigate(R.id.wishListFragment)
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

}