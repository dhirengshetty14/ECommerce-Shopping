package com.projs.ecommerceshopping.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import android.view.View
import com.projs.ecommerceshopping.R
import androidx.core.view.GravityCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.projs.ecommerceshopping.databinding.ActivityHomeBinding
import com.projs.ecommerceshopping.model.CartFragment
import com.projs.ecommerceshopping.model.CategoryFragment
import com.projs.ecommerceshopping.repository.CategoryRepository
import com.projs.ecommerceshopping.utils.SessionManager
import com.projs.ecommerceshopping.viewmodel.HomeViewModel
import com.projs.ecommerceshopping.viewmodel.HomeViewModelFactory

class Home : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        session = SessionManager(this)

        setupListeners()
        setupDrawer()

        // Default screen
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CategoryFragment())
            .commit()
    }

    private fun setupListeners() {

        binding.ivMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.ivSearch.setOnClickListener {
            binding.searchLayout.visibility = View.VISIBLE
        }

        binding.ivClear.setOnClickListener {
            binding.searchLayout.visibility = View.GONE
            binding.etSearch.setText("")
        }
    }

    private fun setupDrawer() {

        binding.navigationView.setNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, CategoryFragment())
                        .commit()
                }

                R.id.nav_cart -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, CartFragment())
                        .commit()
                }

                R.id.nav_logout -> {
                    session.logout()
                    startActivity(Intent(this, Login::class.java))
                    finish()
                }
            }

            binding.drawerLayout.closeDrawers()
            true
        }
    }
}