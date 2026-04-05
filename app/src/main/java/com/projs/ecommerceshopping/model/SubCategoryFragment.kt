package com.projs.ecommerceshopping.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.projs.ecommerceshopping.R
import com.projs.ecommerceshopping.databinding.FragmentSubCategoryBinding
import com.projs.ecommerceshopping.repository.SubCategoryRepository
import com.projs.ecommerceshopping.viewmodel.SubCategoryViewModel
import com.projs.ecommerceshopping.viewmodel.SubCategoryViewModelFactory



class SubCategoryFragment : Fragment() {

    private lateinit var binding: FragmentSubCategoryBinding
    private lateinit var viewModel: SubCategoryViewModel

    private lateinit var categoryId: String
    private lateinit var categoryName: String

    companion object {
        fun newInstance(id: String, name: String): SubCategoryFragment {
            val fragment = SubCategoryFragment()
            val bundle = Bundle()
            bundle.putString("id", id)
            bundle.putString("name", name)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            categoryId = it.getString("id") ?: ""
            categoryName = it.getString("name") ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSubCategoryBinding.inflate(inflater, container, false)

        val repository = SubCategoryRepository()
        val factory = SubCategoryViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[SubCategoryViewModel::class.java]

        binding.tvTitle.text = categoryName

        observeViewModel()

        viewModel.fetchSubCategories(categoryId)

        return binding.root
    }

    private fun observeViewModel() {

        viewModel.subCategories.observe(viewLifecycleOwner) { list ->

            val adapter = SubCategoryPagerAdapter(this, list)
            binding.viewPager.adapter = adapter

            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = list[position].subcategory_name
            }.attach()
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}