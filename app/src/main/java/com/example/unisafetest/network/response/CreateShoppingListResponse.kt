package com.example.unisafetest.network.response

import com.google.gson.annotations.SerializedName

data class CreateShoppingListResponse(
    @SerializedName("success")
    var success: Boolean,

    @SerializedName("list_id")
    var listId: Int
)
