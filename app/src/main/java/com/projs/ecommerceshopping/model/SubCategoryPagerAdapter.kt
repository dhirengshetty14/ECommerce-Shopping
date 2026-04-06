package com.projs.ecommerceshopping.model

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SubCategoryPagerAdapter(
    fragment: Fragment,
    private val size: Int
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = size

    override fun createFragment(position: Int): Fragment {
        return ProductFragment()
    }
}