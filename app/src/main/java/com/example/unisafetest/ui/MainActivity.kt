package com.example.unisafetest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.unisafetest.databinding.ActivityMainBinding
import com.example.unisafetest.ui.viewmodel.ShopListViewModel
import com.example.unisafetest.ui.viewmodel.AuthenticationViewModel

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var allMyShopListsViewModel: ShopListViewModel
    private lateinit var authViewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instantiateViewModels()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun instantiateViewModels() {
        allMyShopListsViewModel = ViewModelProvider(this)[ShopListViewModel::class.java]
        authViewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}