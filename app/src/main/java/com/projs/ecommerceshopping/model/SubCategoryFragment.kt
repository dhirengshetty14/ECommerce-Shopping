package com.projs.ecommerceshopping.model

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.projs.ecommerceshopping.databinding.FragmentSubCategoryBinding
import com.projs.ecommerceshopping.repository.SubCategoryRepository
import com.projs.ecommerceshopping.viewmodel.SubCategorySharedViewModel
import com.projs.ecommerceshopping.viewmodel.SubCategoryViewModel
import com.projs.ecommerceshopping.viewmodel.SubCategoryViewModelFactory

class SubCategoryFragment : Fragment() {

    private lateinit var binding: FragmentSubCategoryBinding
    private lateinit var viewModel: SubCategoryViewModel

    private val sharedVM: SubCategorySharedViewModel by activityViewModels()

    private val args: SubCategoryFragmentArgs by navArgs()

    private lateinit var categoryId: String
    private lateinit var categoryName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryId = args.id
        categoryName = args.name
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

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

            if (list.isNotEmpty()) {
                sharedVM.subCategoryId.value = list[0].subcategory_id
            }

            val adapter = SubCategoryPagerAdapter(this, list.size)
            binding.viewPager.adapter = adapter

            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = list[position].subcategory_name
            }.attach()

            binding.viewPager.registerOnPageChangeCallback(object : androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    sharedVM.subCategoryId.value = list[position].subcategory_id
                }
            })
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}