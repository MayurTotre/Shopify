package com.example.shopify.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopify.R
import com.example.shopify.model.response.ProductsResponseItem

class WishListAdapter(private val productList: List<ProductsResponseItem>):RecyclerView.Adapter<WishListAdapter.WishListViewHolder>() {
    class WishListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mainImage: ImageView = itemView.findViewById(R.id.productImageClothes)
        val productName: TextView = itemView.findViewById(R.id.itemName)
        val productPrice: TextView = itemView.findViewById(R.id.itemPrice)
        val wishListButton: ImageView = itemView.findViewById(R.id.wishListBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.wish_list_adapter, parent, false)
        return WishListAdapter.WishListViewHolder(view)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: WishListViewHolder, position: Int) {
        val productsData = productList[position]
        Glide.with(holder.itemView.context)
            .load(productsData.images[0])
            .into(holder.mainImage)

        holder.productPrice.text = "$${productsData.price.toString()}"
        holder.productName.text = productsData.title

    }
}