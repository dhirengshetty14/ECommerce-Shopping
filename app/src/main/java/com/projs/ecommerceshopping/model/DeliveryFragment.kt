package com.projs.ecommerceshopping.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.projs.ecommerceshopping.databinding.DialogAddAddressBinding
import com.projs.ecommerceshopping.databinding.FragmentDeliveryBinding
import com.projs.ecommerceshopping.model.local.AppDatabase
import com.projs.ecommerceshopping.repository.AddressRepository
import com.projs.ecommerceshopping.utils.SessionManager
import com.projs.ecommerceshopping.view.AddressAdapter
import com.projs.ecommerceshopping.viewmodel.AddressViewModel
import com.projs.ecommerceshopping.viewmodel.AddressViewModelFactory
import com.projs.ecommerceshopping.viewmodel.CheckoutSharedViewModel

class DeliveryFragment : Fragment() {

    private lateinit var binding: FragmentDeliveryBinding
    private lateinit var viewModel: AddressViewModel
    private lateinit var adapter: AddressAdapter

    private val sharedVM: CheckoutSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDeliveryBinding.inflate(inflater, container, false)

        val db = AppDatabase.getDatabase(requireContext())
        val repo = AddressRepository(db.addressDao())

        viewModel = ViewModelProvider(
            this,
            AddressViewModelFactory(repo)
        )[AddressViewModel::class.java]

        setupRecycler()
        observeData()

        val session = SessionManager(requireContext())
        val userId = session.getUserId()

        viewModel.fetchAddresses(userId)

        binding.btnAddAddress.setOnClickListener {
            showAddAddressDialog(userId)
        }

        binding.btnNextDelivery.setOnClickListener {
            (parentFragment as CheckoutFragment).goToTab(2)
        }

        return binding.root
    }

    private fun setupRecycler() {
        binding.rvAddress.layoutManager = LinearLayoutManager(requireContext())

        adapter = AddressAdapter(mutableListOf()) { selected ->
            sharedVM.selectedAddress.value = selected.title + "\n" + selected.address
        }

        binding.rvAddress.adapter = adapter
    }

    private fun observeData() {
        viewModel.addresses.observe(viewLifecycleOwner) { list ->
            adapter.updateList(list.toMutableList())

            if (list.isNotEmpty() && sharedVM.selectedAddress.value == null) {
                sharedVM.selectedAddress.value = list[0].title + "\n" + list[0].address
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun showAddAddressDialog(userId: String) {

        val dialogBinding = DialogAddAddressBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .setCancelable(false)
            .create()

        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.btnSave.setOnClickListener {

            val title = dialogBinding.etTitle.text.toString().trim()
            val address = dialogBinding.etAddress.text.toString().trim()

            if (title.isEmpty() || address.isEmpty()) {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.addAddress(userId, title, address)

            dialog.dismiss()
        }

        dialog.show()
    }
}