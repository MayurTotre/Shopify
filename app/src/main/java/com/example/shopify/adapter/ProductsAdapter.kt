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
import com.example.shopify.interfaces.OnClickDoAction
import com.example.shopify.interfaces.OnClickGetProductId
import com.example.shopify.model.response.ProductsResponse

class ProductsAdapter(private val productsList: ProductsResponse, private val onClickDoAction: OnClickDoAction, private val onClickGetProductId: OnClickGetProductId): RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>(){
    class ProductsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mainImage: ImageView = itemView.findViewById(R.id.productImageClothes)
        val productName: TextView = itemView.findViewById(R.id.itemName)
        val productPrice: TextView = itemView.findViewById(R.id.itemPrice)
        val wishListButton: ImageView = itemView.findViewById(R.id.wishListBtn)
        val productCard: CardView = itemView.findViewById(R.id.cvProduct)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.products_adapter, parent, false)
        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int = productsList.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val productsData = productsList[position]
        Glide.with(holder.itemView.context)
            .load(productsData.images[0])
            .into(holder.mainImage)

        holder.productPrice.text = "$${productsData.price.toString()}"
        holder.productName.text = productsData.title

        holder.wishListButton.setOnClickListener {
            onClickDoAction.onCliCkDoAction(productsData)
        }

        holder.productCard.setOnClickListener {
            onClickGetProductId.onClickGetProductId(productsData.id)
        }

    }
}