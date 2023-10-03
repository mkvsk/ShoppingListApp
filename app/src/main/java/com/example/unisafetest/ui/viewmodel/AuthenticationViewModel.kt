package com.example.unisafetest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unisafetest.network.callback.ResultCallback
import com.example.unisafetest.network.response.AuthenticationResponse
import com.example.unisafetest.ui.repository.AuthenticationRepository

class AuthenticationViewModel : ViewModel() {
    private val repository by lazy { AuthenticationRepository() }

    private val _isUserAuthenticated = MutableLiveData(false)
    val isUserAuthenticated: LiveData<Boolean> get() = _isUserAuthenticated

    fun setIsUserAuthenticated(value: Boolean) {
        _isUserAuthenticated.value = value
    }

    private val _userKey = MutableLiveData("")
    val userKey: LiveData<String> get() = _userKey

    fun setKey(value: String) {
        _userKey.value = value
    }

    fun getKey() {
        repository.createTestKey(object : ResultCallback<String> {
            override fun onResult(value: String?) {
                value?.let {
                    setKey(it)
                }
            }

            override fun onFailure(value: String?) {

            }
        })
    }

    fun authorize(key: String) {
        repository.authentication(key, object : ResultCallback<AuthenticationResponse> {
            override fun onResult(value: AuthenticationResponse?) {
                value?.let {
                    setIsUserAuthenticated(it.success)
                }
            }

            override fun onFailure(value: AuthenticationResponse?) {

            }
        })
    }
}