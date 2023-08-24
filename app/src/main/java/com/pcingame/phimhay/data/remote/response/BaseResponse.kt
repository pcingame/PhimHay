package com.pcingame.phimhay.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse<T>(
    @Expose
    @SerializedName("data")
    var data: T,
    @Expose
    @SerializedName("description")
    val description: String = "",
    @Expose
    @SerializedName("code")
    val code: Int = 0
) {

    override fun equals(other: Any?): Boolean =
        other is BaseResponse<*>
                && data == other.data
                && description == other.description
                && code == other.code

    override fun toString() = """
        {
            data: $data, 
            description: $description, 
            code: $code
        }
    """.trimIndent()

    override fun hashCode() = javaClass.hashCode()
}
