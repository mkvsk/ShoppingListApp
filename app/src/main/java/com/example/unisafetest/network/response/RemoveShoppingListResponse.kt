package com.example.unisafetest.network.response

import com.google.gson.annotations.SerializedName

data class RemoveShoppingListResponse(
    @SerializedName("success")
    var success: Boolean,

    @SerializedName("new_value")
    var newValue: Boolean
)