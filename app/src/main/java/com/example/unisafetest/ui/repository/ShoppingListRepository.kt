package com.example.unisafetest.ui.repository

import android.util.Log
import com.example.unisafetest.network.response.CreateShoppingListResponse
import com.example.unisafetest.network.RetrofitFactory
import com.example.unisafetest.network.callback.ResultCallback
import com.example.unisafetest.network.response.GetAllMyShopListsResponse
import com.example.unisafetest.network.response.GetShoppingListResponse
import com.example.unisafetest.network.response.RemoveShoppingListResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class ShoppingListRepository {
    companion object {
        const val TAG = "ShoppingListRepository"
    }

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun getAllMyShopLists(key: String, callback: ResultCallback<GetAllMyShopListsResponse>) {
        scope.launch(Dispatchers.IO) {
            val call = RetrofitFactory.apiService().getAllMyShopLists(key)

            call.enqueue(object : Callback<GetAllMyShopListsResponse> {
                override fun onResponse(
                    call: Call<GetAllMyShopListsResponse>,
                    response: Response<GetAllMyShopListsResponse>
                ) {
                    if (response.code() == 200 || response.code() == 201) {
                        Log.d(TAG, "GET ALL MY SHOP LISTS OK ${response.body().toString()}")
                        callback.onResult(response.body())
                    } else {
                        Log.d(TAG, "GET ALL MY SHOP LISTS ERROR ${response.raw().toString()}")
                        callback.onFailure(null)
                    }
                }

                override fun onFailure(call: Call<GetAllMyShopListsResponse>, t: Throwable) {
                    t.printStackTrace()
                    callback.onFailure(null)
                }
            })
        }
    }

    fun createShoppingList(
        key: String,
        name: String,
        callback: ResultCallback<CreateShoppingListResponse>
    ) {
        scope.launch(Dispatchers.IO) {
            val call = RetrofitFactory.apiService().createShoppingList(key, name)
            call.enqueue(object : Callback<CreateShoppingListResponse> {
                override fun onResponse(
                    call: Call<CreateShoppingListResponse>,
                    response: Response<CreateShoppingListResponse>
                ) {
                    if (response.code() == 200 || response.code() == 201) {
                        Log.d(TAG, "CREATE SHOPPING LIST OK ${response.body().toString()}")
                        callback.onResult(response.body())
                    } else {
                        Log.d(TAG, "CREATE SHOPPING LIST ERROR ${response.raw().toString()}")
                        callback.onFailure(null)
                    }
                }

                override fun onFailure(call: Call<CreateShoppingListResponse>, t: Throwable) {
                    t.printStackTrace()
                    callback.onFailure(null)
                }
            })
        }
    }

    fun getShoppingList(id: Int, callback: ResultCallback<GetShoppingListResponse>) {
        scope.launch(Dispatchers.IO) {
            val call = RetrofitFactory.apiService().getShoppingList(id)

            call.enqueue(object : Callback<GetShoppingListResponse> {
                override fun onResponse(
                    call: Call<GetShoppingListResponse>,
                    response: Response<GetShoppingListResponse>
                ) {
                    if (response.code() == 200 || response.code() == 201) {
                        Log.d(TAG, "GET SHOPPING LIST OK ${response.body().toString()}")
                    } else {
                        Log.d(TAG, "GET SHOPPING LIST ERROR")
                        callback.onFailure(null)
                    }
                }

                override fun onFailure(call: Call<GetShoppingListResponse>, t: Throwable) {
                    t.printStackTrace()
                    callback.onFailure(null)
                }
            })
        }
    }

    fun removeShoppingList(listId: Int, callback: ResultCallback<RemoveShoppingListResponse>) {
        scope.launch(Dispatchers.IO) {
            val call = RetrofitFactory.apiService().removeShoppingList(listId)

            call.enqueue(object : Callback<RemoveShoppingListResponse> {
                override fun onResponse(
                    call: Call<RemoveShoppingListResponse>,
                    response: Response<RemoveShoppingListResponse>
                ) {
                    if (response.code() == 200 || response.code() == 201) {
                        Log.d(TAG, "REMOVE SHOPPING LIST OK ${response.body().toString()}")
                    } else {
                        Log.d(TAG, "REMOVE SHOPPING LIST ERROR")
                        callback.onFailure(null)
                    }
                }

                override fun onFailure(call: Call<RemoveShoppingListResponse>, t: Throwable) {
                    t.printStackTrace()
                    callback.onFailure(null)
                }
            })
        }
    }
}