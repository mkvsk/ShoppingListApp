package com.example.unisafetest.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.unisafetest.databinding.ActivityMainBinding
import com.example.unisafetest.viewmodel.AllMyShopListsViewModel
import com.example.unisafetest.viewmodel.AuthenticationViewModel

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var allMyShopListsViewModel: AllMyShopListsViewModel
    private lateinit var authViewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instantiateViewModels()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun instantiateViewModels() {
        allMyShopListsViewModel = ViewModelProvider(this)[AllMyShopListsViewModel::class.java]
        authViewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}