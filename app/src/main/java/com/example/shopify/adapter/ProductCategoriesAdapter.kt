package com.example.shopify.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopify.R
import com.example.shopify.model.response.CategoriesResponse

class ProductCategoriesAdapter(private val categoriesList: CategoriesResponse) :
    RecyclerView.Adapter<ProductCategoriesAdapter.ProductCategoriesViewHolder>() {
    class ProductCategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryImage: ImageView = itemView.findViewById(R.id.ivCategory)
        val categoryText: TextView = itemView.findViewById(R.id.tvCategoryText)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductCategoriesViewHolder {
        val view =LayoutInflater.from(parent.context)
            .inflate(R.layout.category_adapter, parent, false)
        return ProductCategoriesViewHolder(view)
    }

    override fun getItemCount(): Int = categoriesList.size

    override fun onBindViewHolder(holder: ProductCategoriesViewHolder, position: Int) {
        val categoriesData = categoriesList[position]
        Glide.with(holder.itemView.context)
            .load(categoriesData.image)
            .into(holder.categoryImage)

        holder.categoryText.text = categoriesData.name
    }

}