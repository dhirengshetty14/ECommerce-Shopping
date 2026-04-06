package com.projs.ecommerceshopping.model

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.projs.ecommerceshopping.R
import com.projs.ecommerceshopping.adapter.CartReadonlyAdapter
import com.projs.ecommerceshopping.databinding.FragmentCartItemsBinding
import com.projs.ecommerceshopping.model.local.AppDatabase
import com.projs.ecommerceshopping.repository.CartRepository
import com.projs.ecommerceshopping.repository.ICartRepository
import com.projs.ecommerceshopping.viewmodel.CartViewModel
import com.projs.ecommerceshopping.viewmodel.CartViewModelFactory

class CartItemsFragment : Fragment() {

    private lateinit var binding: FragmentCartItemsBinding
    private lateinit var viewModel: CartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentCartItemsBinding.inflate(inflater, container, false)

        val db = AppDatabase.getDatabase(requireContext())
        val repo: ICartRepository = CartRepository(db.cartDao())

        viewModel = ViewModelProvider(this, CartViewModelFactory(repo))[CartViewModel::class.java]

        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())

        viewModel.cartItems.observe(viewLifecycleOwner) { list ->

            binding.rvCart.adapter = CartReadonlyAdapter(list)

            val total = list.sumOf { it.price.toInt() * it.quantity }
            binding.tvTotal.text = "Total: $$total"
        }

        binding.btnNextCart.setOnClickListener {
            requireActivity()
                .findViewById<ViewPager2>(R.id.viewPager)
                .currentItem = 1
        }

        return binding.root
    }
}