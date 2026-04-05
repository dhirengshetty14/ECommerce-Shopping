package com.projs.ecommerceshopping.model

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SubCategoryPagerAdapter(
    fragment: Fragment,
    private val subCategories: List<String>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = subCategories.size

    override fun createFragment(position: Int): Fragment {
        return ProductFragment.newInstance(subCategories[position])
    }
}