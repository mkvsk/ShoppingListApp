package com.example.unisafetest.network

import com.example.unisafetest.network.response.AddToShoppingListResponse
import com.example.unisafetest.network.response.AuthenticationResponse
import com.example.unisafetest.network.response.CreateShoppingListResponse
import com.example.unisafetest.network.response.GetAllMyShopListsResponse
import com.example.unisafetest.network.response.GetShoppingListResponse
import com.example.unisafetest.network.response.RemoveFromListResponse
import com.example.unisafetest.network.response.RemoveShoppingListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkInfoService {
    @GET("v1/CreateTestKey?")
    fun createTestKey(): Call<String>

    @GET("v1/Authentication?")
    fun authentication(@Query(value = "key") key: String): Call<AuthenticationResponse>

    @GET("v1/CreateShoppingList?")
    fun createShoppingList(
        @Query(value = "key") key: String,
        @Query(value = "name") name: String
    ): Call<CreateShoppingListResponse>

    @GET("v1/RemoveShoppingList?")
    fun removeShoppingList(@Query(value = "list_id") listId: Int): Call<RemoveShoppingListResponse>

    @GET("v1/AddToShoppingList?")
    fun addToShoppingList(
        @Query(value = "id") id: Int,
        @Query(value = "name") name: String,
        @Query(value = "value") value: Int
    ): Call<AddToShoppingListResponse>

    //    WTF
//    @GET("v1/CrossItOff?")
//    fun crossItOff(@Query(value = "id") id: Int): Call<RemoveShoppingListResponse>

    @GET("v2/GetShoppingList?")
    fun getShoppingList(@Query(value = "id") id: Int): Call<GetShoppingListResponse>

    @GET("v1/GetAllMyShopLists?")
    fun getAllMyShopLists(@Query(value = "key") key: String): Call<GetAllMyShopListsResponse>

    @GET("v2/RemoveFromList")
    fun removeFromList(
        @Query(value = "list_id") listId: Int,
        @Query(value = "item_id") itemId: Int
    ): Call<RemoveFromListResponse>
}
