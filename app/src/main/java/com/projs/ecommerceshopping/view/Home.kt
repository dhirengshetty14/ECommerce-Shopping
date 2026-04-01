package com.projs.ecommerceshopping.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.projs.ecommerceshopping.databinding.ActivityHomeBinding
import com.projs.ecommerceshopping.utils.SessionManager

class Home : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        session = SessionManager(this)

        setupListeners()
    }

    private fun setupListeners() {

        binding.btnLogout.setOnClickListener {

            session.logout()

            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }
}