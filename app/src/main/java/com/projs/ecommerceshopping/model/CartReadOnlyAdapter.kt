package com.projs.ecommerceshopping.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projs.ecommerceshopping.databinding.ItemCartReadonlyBinding
import com.projs.ecommerceshopping.model.local.CartItem
import com.squareup.picasso.Picasso

class CartReadonlyAdapter(
    private val list: List<CartItem>
) : RecyclerView.Adapter<CartReadonlyAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCartReadonlyBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCartReadonlyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.binding.tvName.text = item.product_name
        holder.binding.tvPrice.text = "$${item.price}"
        holder.binding.tvQuantity.text = "Quantity: ${item.quantity}"

        val amount = item.price.toInt() * item.quantity
        holder.binding.tvAmount.text = "$$amount"

        Picasso.get()
            .load("http://103.163.198.93/myshop/images/${item.image}")
            .into(holder.binding.ivProduct)
    }

    override fun getItemCount() = list.size
}