package com.example.unisafetest.network.response

import com.google.gson.annotations.SerializedName

data class AddToShoppingListResponse(
    @SerializedName("success")
    var success: Boolean,

    @SerializedName("item_id")
    var itemId: Int
)