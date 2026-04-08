package com.projs.ecommerceshopping.model

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.projs.ecommerceshopping.databinding.FragmentProductDetailsBinding
import com.projs.ecommerceshopping.model.local.AppDatabase
import com.projs.ecommerceshopping.model.local.CartItem
import com.projs.ecommerceshopping.repository.CartRepository
import com.projs.ecommerceshopping.repository.ICartRepository
import com.projs.ecommerceshopping.repository.ProductRepository
import com.projs.ecommerceshopping.view.ReviewAdapter
import com.projs.ecommerceshopping.view.SpecAdapter
import com.projs.ecommerceshopping.viewmodel.CartViewModel
import com.projs.ecommerceshopping.viewmodel.CartViewModelFactory
import com.projs.ecommerceshopping.viewmodel.ProductViewModel
import com.projs.ecommerceshopping.viewmodel.ProductViewModelFactory

class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var viewModel: ProductViewModel

    private lateinit var cartViewModel: CartViewModel

    private val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        val repo = ProductRepository()
        val factory = ProductViewModelFactory(repo)
        viewModel = ViewModelProvider(this, factory)[ProductViewModel::class.java]

        val db = AppDatabase.getDatabase(requireContext())
        val cartRepo: ICartRepository = CartRepository(db.cartDao())
        cartViewModel = ViewModelProvider(this, CartViewModelFactory(cartRepo))[CartViewModel::class.java]

        val productId = args.productId

        viewModel.fetchProductDetails(productId)

        observeData()

        return binding.root
    }

    private fun observeData() {
        viewModel.productDetails.observe(viewLifecycleOwner) { product ->

            binding.tvName.text = product.product_name
            binding.tvDesc.text = product.description
            binding.tvPrice.text = "$${product.price}"

            val images = product.images.map { it.image }

            val pagerAdapter = ImagePagerAdapter(images)
            binding.viewPager.adapter = pagerAdapter
            binding.dotsIndicator.setViewPager2(binding.viewPager)

            binding.rvSpecs.layoutManager = LinearLayoutManager(requireContext())
            binding.rvSpecs.adapter = SpecAdapter(product.specifications)

            binding.rvReviews.layoutManager = LinearLayoutManager(requireContext())

            if (product.reviews.isNullOrEmpty()) {
                binding.tvNoReviews.visibility = View.VISIBLE
                binding.rvReviews.visibility = View.GONE
            } else {
                binding.tvNoReviews.visibility = View.GONE
                binding.rvReviews.visibility = View.VISIBLE
                binding.rvReviews.adapter = ReviewAdapter(product.reviews)
            }

            binding.btnAddToCart.setOnClickListener {

                val item = CartItem(
                    product_id = product.product_id,
                    product_name = product.product_name,
                    price = product.price,
                    image = product.product_image_url,
                    quantity = 1
                )

                cartViewModel.insert(item)

                Toast.makeText(requireContext(), "Added to Cart!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}