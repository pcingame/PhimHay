package com.pcingame.phimhay.common.error

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(
    @Expose
    @SerializedName("success")
    val success: Boolean,
    @Expose
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("status_message")
    val statusMessage: String? = null,
)
