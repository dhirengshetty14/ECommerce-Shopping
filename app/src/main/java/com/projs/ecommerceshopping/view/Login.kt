package com.projs.ecommerceshopping.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.projs.ecommerceshopping.databinding.ActivityLoginBinding
import com.projs.ecommerceshopping.repository.AuthRepository
import com.projs.ecommerceshopping.utils.SessionManager
import com.projs.ecommerceshopping.viewmodel.AuthViewModel
import com.projs.ecommerceshopping.viewmodel.AuthViewModelFactory

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        session = SessionManager(this)
        if(session.isLoggedIn()){
            startActivity(Intent(this, Home::class.java))
            finish()
            return
        }

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
        binding.tvIdhaccount.setOnClickListener {
            val intent= Intent(this, Register::class.java)
            startActivity(intent)
        }
        binding.tvForgotPwd.setOnClickListener {
            Toast.makeText(this, "Forgot Password Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {

        viewModel.loginResult.observe(this) {
            if (it.status == 0 && it.user!=null) {
                //saving session
                session.saveUser(
                    it.user.user_id,
                    it.user.full_name,
                    it.user.email_id,
                    it.user.mobile_no
                )
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

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