package com.example.shopify.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.shopify.R
import com.example.shopify.viewmodel.CategoriesViewModel
import com.example.shopify.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        viewModel.categories()
        viewModel.categories.observe(this){result ->
            result.onSuccess {
                Log.d("Categories",  "${it}")
            }
            result.onFailure {
                Log.d("Categories", "${it.message}")
            }
        }
    }
}