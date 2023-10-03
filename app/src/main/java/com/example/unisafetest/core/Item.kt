package com.example.unisafetest.core

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("created")
    var created: String = "",

    @SerializedName("name")
    var name: String = "",

    @SerializedName("is_crossed")
    var isCrossed: Boolean? = null,

    @SerializedName("id")
    var id: Int? = null
)
