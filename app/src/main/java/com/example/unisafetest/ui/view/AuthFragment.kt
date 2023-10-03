package com.example.unisafetest.ui.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.unisafetest.R
import com.example.unisafetest.databinding.FragmentAuthBinding
import com.example.unisafetest.util.Constants.DEFAULT_VALUE
import com.example.unisafetest.util.Constants.SP_TAG
import com.example.unisafetest.util.Constants.SP_TAG_USER_KEY
import com.example.unisafetest.util.obtainViewModel
import com.example.unisafetest.ui.viewmodel.AuthenticationViewModel

class AuthFragment : Fragment() {
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private val authViewModel by lazy { obtainViewModel(AuthenticationViewModel::class.java) }
    private lateinit var sharedPreferences: SharedPreferences
    private var userKey: String? = null

    companion object {
        const val TAG = "AuthFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: ")
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initListeners()
        initObservers()
    }

    private fun initViews() {
        loadSharedPreferences()
    }

    private fun initListeners() {
        binding.run {
            btnGetKey.setOnClickListener {
                authViewModel.getKey()
            }

            btnSubmit.setOnClickListener {
                authViewModel.authorize(etKey.text.toString())
            }
        }

    }

    private fun initObservers() {
        authViewModel.userKey.observe(viewLifecycleOwner) {
            userKey = it.toString()
            binding.etKey.setText(userKey)
        }

        authViewModel.isUserAuthenticated.observe(viewLifecycleOwner) {
            if (it) {
                saveUserToSharedPrefs()
                navigateHome()
            }
        }
    }

    private fun navigateHome() {
        findNavController().navigate(R.id.action_go_to_home)
    }

    //    TODO ref
    private fun loadSharedPreferences() {
        sharedPreferences = requireActivity().getSharedPreferences(SP_TAG, Context.MODE_PRIVATE)
        userKey = sharedPreferences.getString(SP_TAG_USER_KEY, DEFAULT_VALUE)

        if (userKey != null) {
            Toast.makeText(requireContext(), "SP ${userKey.toString()}", Toast.LENGTH_SHORT).show()
            authViewModel.setKey(userKey.toString())
        } else {
            Toast.makeText(requireContext(), "SP IS EMPTY", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveUserToSharedPrefs() {
        val sharedPreferences = requireActivity().getSharedPreferences(SP_TAG, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(SP_TAG_USER_KEY, authViewModel.userKey.value)
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}