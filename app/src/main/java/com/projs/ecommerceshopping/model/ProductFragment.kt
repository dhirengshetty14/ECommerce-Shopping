package com.projs.ecommerceshopping.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.projs.ecommerceshopping.R

class ProductFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_product, container, false)
    }
}