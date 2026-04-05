package com.projs.ecommerceshopping.model

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.projs.ecommerceshopping.R
import com.projs.ecommerceshopping.adapter.CartReadonlyAdapter
import com.projs.ecommerceshopping.databinding.FragmentSummaryBinding
import com.projs.ecommerceshopping.model.local.*
import com.projs.ecommerceshopping.repository.*
import com.projs.ecommerceshopping.viewmodel.*

class SummaryFragment : Fragment() {

    private lateinit var binding: FragmentSummaryBinding
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentSummaryBinding.inflate(inflater, container, false)

        val db = AppDatabase.getDatabase(requireContext())

        val cartRepo: ICartRepository = CartRepository(db.cartDao())
        val orderRepo: IOrderRepository = OrderRepository(db.orderDao())

        cartViewModel = ViewModelProvider(this, CartViewModelFactory(cartRepo))[CartViewModel::class.java]

        val orderViewModel = ViewModelProvider(
            this,
            OrderViewModelFactory(orderRepo)
        )[OrderViewModel::class.java]

        binding.rvSummary.layoutManager = LinearLayoutManager(requireContext())

        cartViewModel.cartItems.observe(viewLifecycleOwner) { list ->

            binding.rvSummary.adapter = CartReadonlyAdapter(list)

            val total = list.sumOf { it.price.toDouble() * it.quantity }
            binding.tvTotal.text = "$$total"
        }

        binding.tvAddress.text = DeliveryFragment.selectedAddress
        binding.tvPayment.text = PaymentFragment.selectedPayment

        binding.btnPlaceOrder.setOnClickListener {

            val cartItems = cartViewModel.cartItems.value ?: emptyList()

            val total = cartItems.sumOf { it.price.toDouble() * it.quantity }

            val order = OrderEntity(
                totalAmount = total,
                address = DeliveryFragment.selectedAddress,
                paymentMethod = PaymentFragment.selectedPayment
            )

            val orderItems = cartItems.map {

                OrderItemEntity(
                    orderId = 0,
                    productName = it.product_name,

                    price = it.price.toDouble(),

                    quantity = it.quantity,
                    image = it.image
                )
            }

            orderViewModel.placeOrder(order, orderItems)

            cartViewModel.clearCart()

            Toast.makeText(requireContext(), "Order Placed!", Toast.LENGTH_SHORT).show()

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, OrderSuccessFragment())
                .commit()
        }

        return binding.root
    }
}