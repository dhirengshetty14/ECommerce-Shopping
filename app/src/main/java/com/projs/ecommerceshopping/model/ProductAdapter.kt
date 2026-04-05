package com.projs.ecommerceshopping.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projs.ecommerceshopping.databinding.ItemProductBinding
import com.projs.ecommerceshopping.model.response.Product

class ProductAdapter(
    private val list: List<Product>
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(
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

        Glide.with(holder.itemView.context)
            .load("http://103.163.198.93/myshop/images/" + item.product_image_url)
            .into(holder.binding.ivProduct)
    }
}