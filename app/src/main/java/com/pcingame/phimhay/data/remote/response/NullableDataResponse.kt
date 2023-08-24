package com.pcingame.phimhay.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NullableDataResponse<T>(
    @Expose
    @SerializedName("data")
    val data: T? = null,
    @Expose
    @SerializedName("description")
    val description: String = "",
    @Expose
    @SerializedName("code")
    val code: Int = 0
)
