package com.projs.ecommerceshopping.model

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.projs.ecommerceshopping.databinding.FragmentDeliveryBinding

class DeliveryFragment : Fragment() {

    private lateinit var binding: FragmentDeliveryBinding

    companion object {
        var selectedAddress = "HOME\n402 W. Marine Way..."
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentDeliveryBinding.inflate(inflater, container, false)

        binding.radioHome.setOnClickListener {
            selectedAddress = "HOME\n402 W. Marine Way..."
        }

        binding.radioOffice.setOnClickListener {
            selectedAddress = "OFFICE\nCottonwood Creek Mall..."
        }
        binding.btnNextDelivery.setOnClickListener {
            (parentFragment as CheckoutFragment).goToTab(2)
        }

        return binding.root
    }
}