package com.projs.ecommerceshopping.view


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projs.ecommerceshopping.R
import com.projs.ecommerceshopping.databinding.ItemOrderBinding
import com.projs.ecommerceshopping.model.local.OrderEntity

class OrdersAdapter(
    private val list: List<OrderEntity>
) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOrderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        holder.binding.tvName.text = "Order #${item.orderId}"

        holder.binding.tvPrice.text = "Total: $${item.totalAmount}"

        holder.binding.tvQty.text = "Items: --"

        holder.binding.tvAddress.text = item.address

        holder.binding.tvPayment.text = item.paymentMethod

        holder.binding.ivProduct.setImageResource(R.drawable.order_icon)
    }
}