package com.pcingame.phimhay.data.remote.response

import com.google.gson.annotations.SerializedName
import com.pcingame.phimhay.domain.mapper.MapAbleToModel
import com.pcingame.phimhay.domain.model.Cast
import com.pcingame.phimhay.domain.model.Crew

data class GetCastAndCrewResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("cast")
    val cast: List<CastRes>? = null,
    @SerializedName("crew")
    val crew: List<CrewRes>? = null
)

data class CrewRes(
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
) : MapAbleToModel<Crew> {
    override fun toModel(): Crew {
        return Crew(creditId, department, gender, id, job, name, profilePath)
    }
}

data class CastRes(
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
) : MapAbleToModel<Cast> {

    override fun toModel(): Cast {
        return Cast(castId, character, creditId, gender, id, name, order, profilePath)
    }
}
