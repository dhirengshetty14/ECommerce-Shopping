package com.projs.ecommerceshopping.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projs.ecommerceshopping.databinding.ItemSpecBinding
import com.projs.ecommerceshopping.model.response.Specification

class SpecAdapter(private val list: List<Specification>) :
    RecyclerView.Adapter<SpecAdapter.VH>() {

    inner class VH(val binding: ItemSpecBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemSpecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = list[position]
        holder.binding.tvTitle.text = item.title
        holder.binding.tvValue.text = item.specification
    }
}