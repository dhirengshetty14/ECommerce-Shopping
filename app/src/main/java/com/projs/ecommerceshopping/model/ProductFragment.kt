package com.projs.ecommerceshopping.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.projs.ecommerceshopping.R

class ProductFragment : Fragment() {

    private lateinit var subCategory: String

    companion object {
        fun newInstance(subCategory: String): ProductFragment {
            val fragment = ProductFragment()
            val bundle = Bundle()
            bundle.putString("subCategory", subCategory)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subCategory = arguments?.getString("subCategory") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_product, container, false)

        // TODO: load products using subCategory

        return view
    }
}