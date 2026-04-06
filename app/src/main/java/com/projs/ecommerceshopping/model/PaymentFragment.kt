package com.projs.ecommerceshopping.model

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.projs.ecommerceshopping.R
import com.projs.ecommerceshopping.databinding.FragmentPaymentBinding
import com.projs.ecommerceshopping.viewmodel.CheckoutSharedViewModel

class PaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding

    private val sharedVM: CheckoutSharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentPaymentBinding.inflate(inflater, container, false)

        if (sharedVM.selectedPayment.value == null) {
            sharedVM.selectedPayment.value = "Cash On Delivery"
        }

        binding.radioCOD.setOnClickListener {
            sharedVM.selectedPayment.value = "Cash On Delivery"
        }

        binding.radioCard.setOnClickListener {
            sharedVM.selectedPayment.value = "Card"
        }

        binding.btnNextPayment.setOnClickListener {
            requireActivity()
                .findViewById<ViewPager2>(R.id.viewPager)
                .currentItem = 3
        }

        return binding.root
    }
}