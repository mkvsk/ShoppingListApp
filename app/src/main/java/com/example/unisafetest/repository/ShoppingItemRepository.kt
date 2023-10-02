package com.example.unisafetest.repository

import android.util.Log
import com.example.unisafetest.network.RetrofitFactory
import com.example.unisafetest.network.callback.ResultCallback
import com.example.unisafetest.network.response.AddToShoppingListResponse
import com.example.unisafetest.network.response.RemoveFromListResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class ShoppingItemRepository {
    companion object {
        const val TAG = "ShoppingItemRepository"
    }

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun addToShoppingList(id: Int, name: String, value: Int, callback: ResultCallback<AddToShoppingListResponse>) {
        scope.launch(Dispatchers.IO) {
            val call = RetrofitFactory.apiService().addToShoppingList(id, name, value)

            call.enqueue(object : Callback<AddToShoppingListResponse> {
                override fun onResponse(
                    call: Call<AddToShoppingListResponse>,
                    response: Response<AddToShoppingListResponse>
                ) {
                    if (response.code() == 200 || response.code() == 201) {
                        Log.d(ShoppingListRepository.TAG, "ADD TO SHOPPING LIST OK ${response.body().toString()}")
                        callback.onResult(response.body())
                    } else {
                        Log.d(ShoppingListRepository.TAG, "ADD TO SHOPPING LIST ERROR ${response.raw().toString()}")
                        callback.onFailure(null)
                    }
                }

                override fun onFailure(call: Call<AddToShoppingListResponse>, t: Throwable) {
                    t.printStackTrace()
                    callback.onFailure(null)
                }
            })
        }
    }

    fun removeFromList(listId: Int, itemId: Int, callback: ResultCallback<RemoveFromListResponse>) {
        scope.launch(Dispatchers.IO) {
            val call = RetrofitFactory.apiService().removeFromList(listId, itemId)

            call.enqueue(object : Callback<RemoveFromListResponse> {
                override fun onResponse(
                    call: Call<RemoveFromListResponse>,
                    response: Response<RemoveFromListResponse>
                ) {
                    if (response.code() == 200 || response.code() == 201) {
                        Log.d(ShoppingListRepository.TAG, "REMOVE FROM LIST OK ${response.body().toString()}")
                        callback.onResult(response.body())
                    } else {
                        Log.d(ShoppingListRepository.TAG, "REMOVE FROM LIST ERROR ${response.raw().toString()}")
                        callback.onFailure(null)
                    }
                }

                override fun onFailure(call: Call<RemoveFromListResponse>, t: Throwable) {
                    t.printStackTrace()
                    callback.onFailure(null)
                }
            })
        }
    }
}