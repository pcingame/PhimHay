package com.pcingame.phimhay.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("password")
    @Expose
    val password: String
)
