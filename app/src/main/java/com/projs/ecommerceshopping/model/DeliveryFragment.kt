package com.projs.ecommerceshopping.model

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.projs.ecommerceshopping.databinding.FragmentDeliveryBinding

class DeliveryFragment : Fragment() {

    private lateinit var binding: FragmentDeliveryBinding

    companion object {
        var selectedAddress = "HOME\nP6 NC 54 Bypass"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentDeliveryBinding.inflate(inflater, container, false)

        binding.radioHome.setOnClickListener {
            selectedAddress = "HOME\n510 NC 54 Bypass, Carrboro, NC"
        }

        binding.radioOffice.setOnClickListener {
            selectedAddress = "OFFICE\n1100 W NC HWY 78 #34, NC"
        }
        binding.btnNextDelivery.setOnClickListener {
            (parentFragment as CheckoutFragment).goToTab(2)
        }

        return binding.root
    }
}