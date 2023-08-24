package com.pcingame.phimhay.domain.model

import com.google.gson.annotations.SerializedName
import com.pcingame.phimhay.BuildConfig

data class MovieCredits(
    val cast: Cast,
    val crew: Crew,
)
data class Crew(
    @SerializedName("credit_id")
    val creditId: String? = null,
    @SerializedName("department")
    val department: String? = null,
    @SerializedName("gender")
    val gender: Int? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("job")
    val job: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null
)

data class Cast(
    @SerializedName("cast_id")
    val castId: String? = null,
    @SerializedName("character")
    val character: String? = null,
    @SerializedName("credit_id")
    val creditId: String? = null,
    @SerializedName("gender")
    val gender: Int? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("order")
    val order: Int? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null
) {

    fun getFullProfilePath() =
        if (profilePath.isNullOrBlank()) null else BuildConfig.SMALL_IMAGE_URL + profilePath
}
