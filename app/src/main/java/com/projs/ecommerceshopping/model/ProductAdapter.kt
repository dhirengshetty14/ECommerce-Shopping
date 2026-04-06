package com.projs.ecommerceshopping.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projs.ecommerceshopping.databinding.ItemProductBinding
import com.projs.ecommerceshopping.model.local.CartItem
import com.projs.ecommerceshopping.model.response.Product

class ProductAdapter(
    private val products: List<Product>,
    private val cartItems: List<CartItem>,
    private val onAdd: (CartItem) -> Unit,
    private val onIncrease: (CartItem) -> Unit,
    private val onDecrease: (CartItem) -> Unit,
    private val onItemClick: (Product) -> Unit
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

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = products[position]

        holder.binding.tvName.text = product.product_name
        holder.binding.tvDesc.text = product.description
        holder.binding.tvPrice.text = "$${product.price}"

        Glide.with(holder.itemView.context)
            .load("http://103.163.198.93/myshop/images/" + product.product_image_url)
            .error(android.R.drawable.ic_dialog_alert)
            .into(holder.binding.ivProduct)

        holder.binding.root.setOnClickListener {
            onItemClick(product)
        }

        val cartItem = cartItems.find { it.product_id == product.product_id }

        if (cartItem != null) {

            holder.binding.tvAddToCart.visibility = View.GONE
            holder.binding.layoutQuantity.visibility = View.VISIBLE

            holder.binding.tvQty.text = cartItem.quantity.toString()

            holder.binding.btnPlus.setOnClickListener {
                onIncrease(cartItem)
            }

            holder.binding.btnMinus.setOnClickListener {
                onDecrease(cartItem)
            }

        } else {

            holder.binding.tvAddToCart.visibility = View.VISIBLE
            holder.binding.layoutQuantity.visibility = View.GONE

            holder.binding.tvAddToCart.setOnClickListener {

                val item = CartItem(
                    product_id = product.product_id,
                    product_name = product.product_name,
                    price = product.price,
                    image = product.product_image_url,
                    quantity = 1
                )

                onAdd(item)
            }
        }
    }
}