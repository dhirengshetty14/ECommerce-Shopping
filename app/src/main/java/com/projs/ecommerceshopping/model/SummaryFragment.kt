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
import com.projs.ecommerceshopping.model.local.AppDatabase
import com.projs.ecommerceshopping.repository.*
import com.projs.ecommerceshopping.viewmodel.*

class SummaryFragment : Fragment() {

    private lateinit var binding: FragmentSummaryBinding
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentSummaryBinding.inflate(inflater, container, false)

        val db = AppDatabase.getDatabase(requireContext())
        val repo: ICartRepository = CartRepository(db.cartDao())

        cartViewModel = ViewModelProvider(this, CartViewModelFactory(repo))[CartViewModel::class.java]

        binding.rvSummary.layoutManager = LinearLayoutManager(requireContext())

        cartViewModel.cartItems.observe(viewLifecycleOwner) { list ->

            binding.rvSummary.adapter = CartReadonlyAdapter(list)

            val total = list.sumOf { it.price.toInt() * it.quantity }
            binding.tvTotal.text = "$$total"
        }

        binding.tvAddress.text = DeliveryFragment.selectedAddress
        binding.tvPayment.text = PaymentFragment.selectedPayment

        binding.btnPlaceOrder.setOnClickListener {

            Toast.makeText(requireContext(), "Order Placed!", Toast.LENGTH_SHORT).show()

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, OrderSuccessFragment())
                .commit()
        }

        return binding.root
    }
}