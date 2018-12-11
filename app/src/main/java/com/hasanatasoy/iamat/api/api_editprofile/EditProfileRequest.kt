package com.hasanatasoy.iamat.api.api_editprofile

import com.hasanatasoy.iamat.profile.profileeditpage.EditProfileDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EditProfileRequest {

    @POST("/editprofile/update")
    fun sendToInformation(@Body editProfileDTO: EditProfileDTO): Call<Boolean>
    @GET("/editprofile/{username}")
    fun getInformation(@Path("username") username: String): Call<EditProfileDTO>
}