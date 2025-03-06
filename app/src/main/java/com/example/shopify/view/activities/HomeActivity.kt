package com.example.shopify.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopify.R
import com.example.shopify.adapter.ProductCategoriesAdapter
import com.example.shopify.databinding.ActivityHomeBinding
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


        viewModel.categories()
        viewModel.categories.observe(this){result ->
            result.onSuccess {categoriesResponse ->
                val data = categoriesResponse
                val categoriesAdapter = ProductCategoriesAdapter(data)
                 binding.rvcategories.layoutManager= LinearLayoutManager(this)
                binding.rvcategories.adapter = categoriesAdapter
                Log.d("Categories",  "${categoriesResponse}")
            }
            result.onFailure {
                Log.d("Categories", "${it.message}")
            }
        }
    }
}