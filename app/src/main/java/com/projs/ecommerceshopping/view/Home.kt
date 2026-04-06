package com.projs.ecommerceshopping.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.projs.ecommerceshopping.R
import com.projs.ecommerceshopping.databinding.ActivityHomeBinding
import com.projs.ecommerceshopping.databinding.NavHeaderBinding
import com.projs.ecommerceshopping.model.CategoryFragment
import com.projs.ecommerceshopping.model.CartFragment
import com.projs.ecommerceshopping.model.OrdersFragment
import com.projs.ecommerceshopping.utils.SessionManager
import com.projs.ecommerceshopping.viewmodel.SearchViewModel

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var session: SessionManager

    private val searchVM: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        session = SessionManager(this)

        setupListeners()
        setupDrawer()

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainer, CategoryFragment())
//            .commit()
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
            searchVM.query.value = ""

            findNavController(R.id.nav_host).popBackStack()
        }

        binding.etSearch.addTextChangedListener {
            val query=it.toString()
            searchVM.query.value=query

            if(query.isNotEmpty()){
                val navController=findNavController(R.id.nav_host)

                if(navController.currentDestination?.id != R.id.productFragment){
                    navController.navigate(R.id.productFragment)
                }
            }
        }
    }

    private fun setupDrawer() {

        val headerBinding = NavHeaderBinding.bind(
            binding.navigationView.getHeaderView(0)
        )

        headerBinding.tvUserName.text = "Welcome ${session.getUserName()}"
        headerBinding.tvEmail.text = session.getEmail()
        headerBinding.tvMobile.text = session.getMobile()

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment

        val navController = navHostFragment.navController
        binding.navigationView.setNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.nav_home -> navController.navigate(R.id.categoryFragment)

                R.id.nav_cart -> navController.navigate(R.id.cartFragment)

                R.id.nav_orders -> navController.navigate(R.id.ordersFragment)

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
    fun setToolbarTitle(title: String) {
        binding.tvTitle.text = title
    }
}