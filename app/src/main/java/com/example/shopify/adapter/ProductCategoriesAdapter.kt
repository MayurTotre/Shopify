package com.example.shopify.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopify.R
import com.example.shopify.interfaces.OnClickGetDetails
import com.example.shopify.model.response.CategoriesResponse

class ProductCategoriesAdapter(private val categoriesList: CategoriesResponse, private val onClieckDetails: OnClickGetDetails) :
    RecyclerView.Adapter<ProductCategoriesAdapter.ProductCategoriesViewHolder>() {
    private var selectedPosition = RecyclerView.NO_POSITION
    class ProductCategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryImage: ImageView = itemView.findViewById(R.id.img_category_item)
        val categoryText: TextView = itemView.findViewById(R.id.tv_category_name)
        val categoryCard: CardView = itemView.findViewById(R.id.cardCategories)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductCategoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
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

        onClieckDetails.getProductById(categoriesData)

        holder.categoryCard.isSelected = position == selectedPosition

        if (position == 0) {
            onClieckDetails.getProductById(categoriesData)
        }

        holder.categoryCard.setOnClickListener {
            val currentPosition = holder.adapterPosition
            if (currentPosition != RecyclerView.NO_POSITION && currentPosition != selectedPosition) {

                val previousPosition = selectedPosition
                selectedPosition = currentPosition

                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)

                onClieckDetails.getProductById(categoriesData)
            }
        }
    }
}