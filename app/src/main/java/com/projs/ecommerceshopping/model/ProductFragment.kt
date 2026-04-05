package com.projs.ecommerceshopping.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.projs.ecommerceshopping.R
import com.projs.ecommerceshopping.databinding.FragmentProductBinding
import com.projs.ecommerceshopping.model.local.AppDatabase
import com.projs.ecommerceshopping.repository.CartRepository
import com.projs.ecommerceshopping.repository.ICartRepository
import com.projs.ecommerceshopping.repository.ProductRepository
import com.projs.ecommerceshopping.viewmodel.CartViewModel
import com.projs.ecommerceshopping.viewmodel.CartViewModelFactory
import com.projs.ecommerceshopping.viewmodel.ProductViewModel
import com.projs.ecommerceshopping.viewmodel.ProductViewModelFactory

class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding
    private lateinit var viewModel: ProductViewModel
    private lateinit var subCategoryId: String

    companion object {
        fun newInstance(subCategoryId: String): ProductFragment {
            val fragment = ProductFragment()
            val bundle = Bundle()
            bundle.putString("subCategoryId", subCategoryId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subCategoryId = arguments?.getString("subCategoryId") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProductBinding.inflate(inflater, container, false)

        val productRepository = ProductRepository()
        val productFactory = ProductViewModelFactory(productRepository)
        viewModel = ViewModelProvider(this, productFactory)[ProductViewModel::class.java]

        val db = AppDatabase.getDatabase(requireContext())
        val cartRepository: ICartRepository = CartRepository(db.cartDao())

        val cartViewModel = ViewModelProvider(
            this,
            CartViewModelFactory(cartRepository)
        )[CartViewModel::class.java]

        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())

        observeViewModel(cartViewModel)

        viewModel.fetchProducts(subCategoryId)

        return binding.root
    }

    private fun observeViewModel(cartViewModel: CartViewModel) {

        cartViewModel.cartItems.observe(viewLifecycleOwner) { cartList ->

            viewModel.products.observe(viewLifecycleOwner) { products ->

                binding.rvProducts.adapter = ProductAdapter(
                    products,
                    cartList,

                    onAdd = {
                        cartViewModel.insert(it)
                    },

                    onIncrease = {
                        it.quantity++
                        cartViewModel.update(it)
                    },

                    onDecrease = {
                        if (it.quantity > 1) {
                            it.quantity--
                            cartViewModel.update(it)
                        } else {
                            cartViewModel.delete(it)
                        }
                    }
                )
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}