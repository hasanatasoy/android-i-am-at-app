package com.hasanatasoy.iamat.profile.profilepage

import com.google.gson.annotations.SerializedName
import com.hasanatasoy.iamat.post.Post

class ProfileViewDTO (
        @SerializedName("photoCount") var photoCount: Int = 0,
        @SerializedName("likeCount") var likeCount: Int = 0,
        @SerializedName("username") var username: String,
        @SerializedName("usernamePpUrl") var userPP: String,
        @SerializedName("name") var name: String,
        @SerializedName("description") var description: String,
        @SerializedName("bio") var bio: String)

