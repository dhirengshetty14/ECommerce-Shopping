package com.projs.ecommerceshopping.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.projs.ecommerceshopping.R
import com.projs.ecommerceshopping.databinding.FragmentCategoryBinding
import com.projs.ecommerceshopping.repository.CategoryRepository
import com.projs.ecommerceshopping.view.CategoryAdapter
import com.projs.ecommerceshopping.viewmodel.HomeViewModel
import com.projs.ecommerceshopping.viewmodel.HomeViewModelFactory


class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        val repository = CategoryRepository()
        val factory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        setupRecyclerView()
        observeViewModel()

        viewModel.fetchCategories()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun observeViewModel() {
        viewModel.categories.observe(viewLifecycleOwner) {
            binding.rvCategories.adapter = CategoryAdapter(it)
        }
    }
}