package com.projs.ecommerceshopping.model

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.projs.ecommerceshopping.model.response.SubCategory

class SubCategoryPagerAdapter(
    fragment: Fragment,
    private val subCategories: List<SubCategory>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = subCategories.size

    override fun createFragment(position: Int): Fragment {
        val item = subCategories[position]

        return ProductFragment.newInstance(
            item.subcategory_id
        )
    }
}