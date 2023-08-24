package com.pcingame.phimhay.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieReview(
    var author: String? = null,
    var content: String? = null,
    var createdAt: String? = null,
    var id: String? = null,
    var updatedAt: String? = null,
    var url: String? = null,
    var name: String? = null,
    var username: String? = null,
    var avatarPath: String? = null,
    var rating: Int? = 8
) : Parcelable