package com.example.shopify.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopify.R
import com.example.shopify.model.response.AddtoCartProductResponse

class CartItemAdapter(private val cartProductsList: List<AddtoCartProductResponse>): RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>() {
    class CartItemViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.iv_cart_product_image)
        val productTitle: TextView = itemView.findViewById(R.id.tv_cart_product_title)
        val productCategory: TextView = itemView.findViewById(R.id.tv_cart_product_category)
        val productPrice: TextView = itemView.findViewById(R.id.tv_cart_product_price)
        val removeButton: ImageButton = itemView.findViewById(R.id.btn_remove)
        val addButton: ImageButton = itemView.findViewById(R.id.btn_add)
        val itemCount: TextView = itemView.findViewById(R.id.tv_no_of_items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item_adapter, parent, false)
            return CartItemViewHolder(view)
    }

    override fun getItemCount() = cartProductsList.size

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val productsData = cartProductsList[position]
        holder.productTitle.text = productsData.title
        Glide.with(holder.productImage.context)
            .load(productsData.images.first())
            .into(holder.productImage)
        holder.productCategory.text = productsData.category.name
        holder.productPrice.text = "${"$" + productsData.price.toString()}"

        holder.addButton.setOnClickListener{
            productsData.quantity++
            holder.itemCount.text = productsData.quantity.toString()
            holder.productPrice.text = "${productsData.price * productsData.quantity}"
        }

        holder.removeButton.setOnClickListener{
            if (productsData.quantity>1){
                productsData.quantity--
                holder.itemCount.text = productsData.quantity.toString()
                holder.productPrice.text = "${productsData.price * productsData.quantity}"
            }
        }
    }
}