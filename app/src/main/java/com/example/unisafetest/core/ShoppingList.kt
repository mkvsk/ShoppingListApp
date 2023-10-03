package com.example.unisafetest.core

import com.google.gson.annotations.SerializedName

data class ShoppingList(
    @SerializedName("created")
    var created: String = "",

    @SerializedName("name")
    var name: String = "",

    @SerializedName("id")
    var id: Int? = null
)
