package com.example.shopify.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify.R
import com.example.shopify.interfaces.OnClickDeleteItem
import com.example.shopify.model.response.AddressResponse


class AddressListAdapter(private val addressList: List<AddressResponse>, val onClickDeleteItem: OnClickDeleteItem): RecyclerView.Adapter<AddressListAdapter.AddressViewHolder>() {
    class AddressViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {
        val radioButton = itemView.findViewById<RadioButton>(R.id.rbAddress)
        val clearSelection = itemView.findViewById<ImageButton>(R.id.btnClear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.address_adapter, parent, false)
        return AddressViewHolder(view)
    }

    override fun getItemCount(): Int  = addressList.size

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val addressData = addressList[position]
        holder.radioButton.text = addressData.address

        holder.clearSelection.setOnClickListener{
            onClickDeleteItem.onClickDeleteItem(addressData.id)
        }
    }
}