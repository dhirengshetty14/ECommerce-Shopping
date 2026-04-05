package com.projs.ecommerceshopping.model

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.projs.ecommerceshopping.databinding.FragmentCartItemsBinding
import com.projs.ecommerceshopping.model.local.AppDatabase
import com.projs.ecommerceshopping.repository.CartRepository
import com.projs.ecommerceshopping.repository.ICartRepository
import com.projs.ecommerceshopping.viewmodel.*

class CartItemsFragment : Fragment() {

    private lateinit var binding: FragmentCartItemsBinding
    private lateinit var viewModel: CartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentCartItemsBinding.inflate(inflater, container, false)

        val db = AppDatabase.getDatabase(requireContext())
        val repo: ICartRepository = CartRepository(db.cartDao())

        viewModel = ViewModelProvider(this, CartViewModelFactory(repo))[CartViewModel::class.java]

        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())

        viewModel.cartItems.observe(viewLifecycleOwner) {
            binding.rvCart.adapter = CartAdapter(it, {}, {})
        }

        return binding.root
    }
}