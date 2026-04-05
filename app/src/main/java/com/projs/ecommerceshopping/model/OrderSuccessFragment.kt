package com.projs.ecommerceshopping.model

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.projs.ecommerceshopping.databinding.FragmentOrderSuccessBinding

class OrderSuccessFragment : Fragment() {

    private lateinit var binding: FragmentOrderSuccessBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentOrderSuccessBinding.inflate(inflater, container, false)

        binding.tvOrderId.text = "#${(1000..9999).random()}"

        return binding.root
    }
}