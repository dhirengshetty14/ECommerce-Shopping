package com.projs.ecommerceshopping.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projs.ecommerceshopping.databinding.ItemCategoryBinding
import com.projs.ecommerceshopping.model.response.Category

class CategoryAdapter(private val list: List<Category>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding= ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item=list[position]
        holder.binding.tvCategoryName.text=item.category_name
        Glide.with(holder.itemView.context)
            .load("http://103.163.198.93/myshop/images/" + item.category_image_url)
            .into(holder.binding.ivCategory)
    }

    override fun getItemCount(): Int=list.size

}