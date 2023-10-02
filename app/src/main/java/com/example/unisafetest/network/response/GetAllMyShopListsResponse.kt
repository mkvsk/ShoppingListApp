package com.example.unisafetest.network.response

import com.example.unisafetest.core.ShoppingList
import com.google.gson.annotations.SerializedName

data class GetAllMyShopListsResponse(
    @SerializedName("shop_list")
    var allMyShopLists: ArrayList<ShoppingList>,

    @SerializedName("success")
    var success: Boolean
)
