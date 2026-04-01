package com.projs.ecommerceshopping.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.projs.ecommerceshopping.databinding.ActivityHomeBinding
import com.projs.ecommerceshopping.repository.CategoryRepository
import com.projs.ecommerceshopping.utils.SessionManager
import com.projs.ecommerceshopping.viewmodel.HomeViewModel
import com.projs.ecommerceshopping.viewmodel.HomeViewModelFactory

class Home : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    private lateinit var session: SessionManager
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        session = SessionManager(this)

        val repository = CategoryRepository()
        val factory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        setupListeners()
        setupRecyclerView()
        observeViewModel()

        viewModel.fetchCategories()
    }

    private fun setupListeners() {

        binding.btnLogout.setOnClickListener {

            session.logout()

            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }

    private fun setupRecyclerView() {
        binding.rvCategories.layoutManager = GridLayoutManager(this, 2)
    }

    private fun observeViewModel() {

        viewModel.categories.observe(this) {
            binding.rvCategories.adapter = CategoryAdapter(it)
        }

        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}