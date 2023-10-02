package com.example.unisafetest.repository

import android.util.Log
import com.example.unisafetest.network.RetrofitFactory
import com.example.unisafetest.network.callback.ResultCallback
import com.example.unisafetest.network.response.AuthenticationResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class AuthenticationRepository {
    companion object {
        const val TAG = "AuthenticationRepository"
    }

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun createTestKey(callback: ResultCallback<String>) {
        scope.launch(Dispatchers.IO) {
            RetrofitFactory.apiService().createTestKey()
                .enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.code() == 200 || response.code() == 201) {
                            Log.d(TAG, "GET KEY OK ${response.body().toString()}")
                            callback.onResult(response.body())
                        } else {
                            Log.d(TAG, "GET KEY ERROR ${response.raw().toString()}")
                            callback.onFailure(null)
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        t.printStackTrace()
                        callback.onFailure(null)
                    }
                })
        }
    }

    fun authentication(key: String, callback: ResultCallback<AuthenticationResponse>) {
        scope.launch(Dispatchers.IO) {
            val call = RetrofitFactory.apiService().authentication(key)

            call.enqueue(object : Callback<AuthenticationResponse> {
                override fun onResponse(
                    call: Call<AuthenticationResponse>,
                    response: Response<AuthenticationResponse>
                ) {
                    if (response.code() == 200 || response.code() == 201) {
                        Log.d(TAG, "AUTHORIZED ${response.body().toString()}")
                        callback.onResult(response.body())
                    } else {
                        Log.d(TAG, "AUTHENTICATION ERROR ${response.raw().toString()}")
                        callback.onFailure(null)
                    }
                }

                override fun onFailure(call: Call<AuthenticationResponse>, t: Throwable) {
                    t.printStackTrace()
                    callback.onFailure(null)
                }
            })
        }
    }
}