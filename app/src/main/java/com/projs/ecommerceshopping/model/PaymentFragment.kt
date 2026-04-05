package com.projs.ecommerceshopping.model

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.projs.ecommerceshopping.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding

    companion object {
        var selectedPayment = "Cash On Delivery"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentPaymentBinding.inflate(inflater, container, false)

        binding.radioCOD.setOnClickListener {
            selectedPayment = "Cash On Delivery"
        }

        binding.radioCard.setOnClickListener {
            selectedPayment = "Card"
        }

        return binding.root
    }
}