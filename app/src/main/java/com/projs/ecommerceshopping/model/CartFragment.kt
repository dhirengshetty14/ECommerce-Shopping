package com.projs.ecommerceshopping.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.projs.ecommerceshopping.R
import com.projs.ecommerceshopping.databinding.FragmentCartBinding
import com.projs.ecommerceshopping.model.local.AppDatabase
import com.projs.ecommerceshopping.repository.CartRepository
import com.projs.ecommerceshopping.repository.ICartRepository
import com.projs.ecommerceshopping.viewmodel.CartViewModel
import com.projs.ecommerceshopping.viewmodel.CartViewModelFactory


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCartBinding.inflate(inflater, container, false)

        val db = AppDatabase.getDatabase(requireContext())
        val repository: ICartRepository = CartRepository(db.cartDao())

        viewModel = ViewModelProvider(
            this,
            CartViewModelFactory(repository)
        )[CartViewModel::class.java]

        setupRecyclerView()
        observeData()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeData() {
        viewModel.cartItems.observe(viewLifecycleOwner) { list ->

            binding.rvCart.adapter = CartAdapter(
                list,
                onIncrease = { item ->
                    item.quantity++
                    viewModel.update(item)
                },
                onDecrease = { item ->
                    if (item.quantity > 1) {
                        item.quantity--
                        viewModel.update(item)
                    } else {
                        viewModel.delete(item)
                    }
                }
            )

            // 🔥 TOTAL CALCULATION
            val total = list.sumOf { it.price.toInt() * it.quantity }
            binding.tvTotal.text = "Total: $$total"
        }
    }
}