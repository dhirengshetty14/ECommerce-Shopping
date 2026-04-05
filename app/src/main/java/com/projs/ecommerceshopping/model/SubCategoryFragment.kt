package com.projs.ecommerceshopping.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.projs.ecommerceshopping.R
import com.projs.ecommerceshopping.databinding.FragmentSubCategoryBinding


class SubCategoryFragment : Fragment() {

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
            val binding= FragmentSubCategoryBinding.inflate(inflater,
                container,
                false)

        binding.tvTitle.text=categoryName

        val tabs=listOf("Android","IPhone","Windows")

        val adapter= SubCategoryPagerAdapter(this,tabs)
        binding.viewPager.adapter=adapter

        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab, position ->
            tab.text=tabs[position]
        }.attach()

        return binding.root
    }
}