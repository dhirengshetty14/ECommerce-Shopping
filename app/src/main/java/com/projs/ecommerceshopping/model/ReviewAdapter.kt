package com.projs.ecommerceshopping.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projs.ecommerceshopping.databinding.ItemReviewBinding
import com.projs.ecommerceshopping.model.response.Review

class ReviewAdapter(private val list: List<Review>) :
    RecyclerView.Adapter<ReviewAdapter.VH>() {

    inner class VH(val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = list[position]
        holder.binding.tvName.text = item.full_name
        holder.binding.tvTitle.text = item.review_title
        holder.binding.tvReview.text = item.review
        holder.binding.tvRating.text = item.rating
    }
}