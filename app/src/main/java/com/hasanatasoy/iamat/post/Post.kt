package com.hasanatasoy.iamat.post

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.*


class Post (
        @SerializedName("id") var id: Int,
        @SerializedName("username") var username: String?,
        @SerializedName("updatedAt") var updateAt: Date?,
        @SerializedName("createdAt") var postTime: Date?,
        @SerializedName("deleted") var deleted:Boolean?,
        @SerializedName("usernameImgUrl") var usernameImgUrl: String?,
        @SerializedName("postImgUrl") var postImgUrl: String?,
        @SerializedName("likeState") var likeState: Boolean?,
        @SerializedName("locationLatitude") var latitude: Double,
        @SerializedName("locationLongtitude") var longtitude: Double)

