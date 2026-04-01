package com.projs.ecommerceshopping.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.projs.ecommerceshopping.databinding.ActivityRegisterBinding
import com.projs.ecommerceshopping.repository.AuthRepository
import com.projs.ecommerceshopping.viewmodel.AuthViewModel
import com.projs.ecommerceshopping.viewmodel.AuthViewModelFactory

class Register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = AuthRepository()
        val factory = AuthViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]

        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {

        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val mobile = binding.etMobile.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            viewModel.register(name, mobile, email, password)
        }

        binding.tvHaveAccount.setOnClickListener {
            finish()
        }
    }

    private fun observeViewModel() {

        viewModel.registerResult.observe(this) {
            if (it.status == 0) {
                Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}