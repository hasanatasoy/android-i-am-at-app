package com.hasanatasoy.iamat.profile.profileeditpage

import com.google.gson.annotations.SerializedName


class EditProfileDTO (
    @SerializedName("username") var username: String,
    @SerializedName("name") var name: String,
    @SerializedName("information") var information: String,
    @SerializedName("bio") var bio: String,
    @SerializedName("email") var email: String,
    @SerializedName("telNo") var telNo: String,
    @SerializedName("gender") var sex: Boolean // false = male true= female
    )
