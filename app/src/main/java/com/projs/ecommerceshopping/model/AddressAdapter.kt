package com.projs.ecommerceshopping.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projs.ecommerceshopping.databinding.ItemAddressBinding
import com.projs.ecommerceshopping.model.local.AddressEntity

class AddressAdapter(
    private var list: MutableList<AddressEntity>,
    private val onSelect: (AddressEntity) -> Unit
) : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {

    private var selectedPosition = 0

    inner class ViewHolder(val binding: ItemAddressBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAddressBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        holder.binding.tvTitle.text = item.title
        holder.binding.tvAddress.text = item.address


        holder.binding.radioButton.isClickable = false

        holder.binding.radioButton.isChecked = position == selectedPosition

        holder.binding.root.setOnClickListener {

            val previous = selectedPosition

            selectedPosition = holder.adapterPosition

            if (previous != -1) notifyItemChanged(previous)
            notifyItemChanged(selectedPosition)

            onSelect(item)
        }
    }

    fun updateList(newList: MutableList<AddressEntity>) {
        list = newList
        notifyDataSetChanged()
    }
}