package com.example.unisafetest.network

import com.example.unisafetest.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private val gson = GsonBuilder().setLenient().create()
    private const val URL = BuildConfig.API_URL

    fun apiService(): NetworkInfoService {
        return Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
            .create(NetworkInfoService::class.java)
    }
}