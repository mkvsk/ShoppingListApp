package com.example.unisafetest.network.response

import com.example.unisafetest.core.Item
import com.google.gson.annotations.SerializedName

data class GetShoppingListResponse(
    @SerializedName("success")
    var success: Boolean,

    @SerializedName("item_list")
    var itemList: ArrayList<Item>
)