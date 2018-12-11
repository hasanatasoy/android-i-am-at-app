package com.hasanatasoy.iamat.post

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

class PostDTO(
        @SerializedName("username") var username: String?,
        @SerializedName("postImg") var imageString: String?,
        @SerializedName("latitude") var latitude: Double,
        @SerializedName("longtitude") var longtitude: Double)

