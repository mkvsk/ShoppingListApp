package com.example.unisafetest.network.response

import com.google.gson.annotations.SerializedName

data class RemoveFromListResponse(
    @SerializedName("success")
    var success: Boolean
)
