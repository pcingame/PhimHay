package com.pcingame.phimhay.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(
    @Expose
    @SerializedName("description")
    val description: List<String> = emptyList(),
    @Expose
    @SerializedName("code")
    val code: Int = 0
)
