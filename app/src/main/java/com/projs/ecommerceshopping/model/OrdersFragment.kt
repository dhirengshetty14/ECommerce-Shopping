package com.projs.ecommerceshopping.model


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.projs.ecommerceshopping.databinding.FragmentOrdersBinding
import com.projs.ecommerceshopping.model.local.AppDatabase
import com.projs.ecommerceshopping.repository.OrderRepository
import com.projs.ecommerceshopping.viewmodel.*

class OrdersFragment : Fragment() {

    private lateinit var binding: FragmentOrdersBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentOrdersBinding.inflate(inflater, container, false)

        val db = AppDatabase.getDatabase(requireContext())
        val repo = OrderRepository(db.orderDao())

        val viewModel = ViewModelProvider(
            this,
            OrderViewModelFactory(repo)
        )[OrderViewModel::class.java]

        binding.rvOrders.layoutManager = LinearLayoutManager(requireContext())

        viewModel.orders.observe(viewLifecycleOwner) {
            binding.rvOrders.adapter = OrdersAdapter(it)
        }

        return binding.root
    }
}