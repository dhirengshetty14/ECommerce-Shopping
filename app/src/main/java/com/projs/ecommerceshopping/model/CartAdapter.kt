package com.projs.ecommerceshopping.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projs.ecommerceshopping.databinding.ItemCartBinding
import com.projs.ecommerceshopping.model.local.CartItem

class CartAdapter(
    private val list: List<CartItem>,
    private val onIncrease: (CartItem) -> Unit,
    private val onDecrease: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        holder.binding.tvName.text = item.product_name
        holder.binding.tvPrice.text = "$${item.price}"
        holder.binding.tvQty.text = item.quantity.toString()

        Glide.with(holder.itemView.context)
            .load("http://103.163.198.93/myshop/images/" + item.image)
            .into(holder.binding.ivProduct)

        holder.binding.btnPlus.setOnClickListener {
            onIncrease(item)
        }

        holder.binding.btnMinus.setOnClickListener {
            onDecrease(item)
        }
    }
}