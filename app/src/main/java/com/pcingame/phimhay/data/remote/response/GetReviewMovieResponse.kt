package com.pcingame.phimhay.data.remote.response

import com.google.gson.annotations.SerializedName
import com.pcingame.phimhay.domain.mapper.MapAbleToModel
import com.pcingame.phimhay.domain.model.MovieReview

data class AuthorDetails(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("username")
    var username: String? = null,
    @SerializedName("avatar_path")
    var avatarPath: String? = null,
    @SerializedName("rating")
    var rating: Int = 8
)

data class GetReviewMovieResponse(
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("author_details")
    var authorDetails: AuthorDetails? = AuthorDetails(),
    @SerializedName("content")
    var content: String? = null,
    @SerializedName("created_at")
    var createdAt: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("updated_at")
    var updatedAt: String? = null,
    @SerializedName("url")
    var url: String? = null
) : MapAbleToModel<MovieReview> {
    override fun toModel(): MovieReview {
        return MovieReview(
            author = author,
            content = content,
            createdAt = createdAt,
            id = id,
            updatedAt = updatedAt,
            url = url,
            name = authorDetails?.name,
            username = authorDetails?.username,
            avatarPath = authorDetails?.avatarPath,
            rating = authorDetails?.rating
        )
    }
}