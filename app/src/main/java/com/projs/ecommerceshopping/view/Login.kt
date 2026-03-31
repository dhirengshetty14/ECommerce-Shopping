package com.projs.ecommerceshopping.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.projs.ecommerceshopping.databinding.ActivityLoginBinding
import com.projs.ecommerceshopping.repository.AuthRepository
import com.projs.ecommerceshopping.viewmodel.AuthViewModel
import com.projs.ecommerceshopping.viewmodel.AuthViewModelFactory

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = AuthRepository()
        val factory = AuthViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]

        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmailId.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            viewModel.login(email, password)
        }
    }

    private fun observeViewModel() {

        viewModel.loginResult.observe(this) {
            if (it.status == 0) {
                startActivity(Intent(this, Home::class.java))
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