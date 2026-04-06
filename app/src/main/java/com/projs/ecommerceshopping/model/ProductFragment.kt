package com.projs.ecommerceshopping.model

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.projs.ecommerceshopping.databinding.FragmentProductBinding
import com.projs.ecommerceshopping.model.local.AppDatabase
import com.projs.ecommerceshopping.repository.CartRepository
import com.projs.ecommerceshopping.repository.ICartRepository
import com.projs.ecommerceshopping.repository.ProductRepository
import com.projs.ecommerceshopping.viewmodel.*

class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding
    private lateinit var viewModel: ProductViewModel

    private val searchVM: SearchViewModel by activityViewModels()
    private val sharedVM: SubCategorySharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentProductBinding.inflate(inflater, container, false)

        val productRepo = ProductRepository()
        val factory = ProductViewModelFactory(productRepo)
        viewModel = ViewModelProvider(this, factory)[ProductViewModel::class.java]

        val db = AppDatabase.getDatabase(requireContext())
        val cartRepo: ICartRepository = CartRepository(db.cartDao())

        val cartVM = ViewModelProvider(
            this,
            CartViewModelFactory(cartRepo)
        )[CartViewModel::class.java]

        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())

        sharedVM.subCategoryId.observe(viewLifecycleOwner) { subCategoryId ->
            viewModel.fetchProducts(subCategoryId)
        }

        observeProducts(cartVM)

        return binding.root
    }

    private fun observeProducts(cartVM: CartViewModel) {

        searchVM.query.observe(viewLifecycleOwner) { query ->
            viewModel.search(query)
        }

        cartVM.cartItems.observe(viewLifecycleOwner) { cartList ->

            viewModel.searchResults.observe(viewLifecycleOwner) { filtered ->

                binding.rvProducts.adapter = ProductAdapter(
                    filtered,
                    cartList,
                    onAdd = { cartVM.insert(it) },
                    onIncrease = {
                        it.quantity++
                        cartVM.update(it)
                    },
                    onDecrease = {
                        if (it.quantity > 1) {
                            it.quantity--
                            cartVM.update(it)
                        } else {
                            cartVM.delete(it)
                        }
                    }
                )
            }
        }
    }
}