package com.hasanatasoy.iamat.api.api_.profile

import com.hasanatasoy.iamat.post.Post
import com.hasanatasoy.iamat.profile.profilepage.ProfileViewDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileRequest {

    @GET("post/all/{username}")
    fun getAllPost(@Path("username") username: String): Call<List<Post>>
    @GET("profile/{username}")
    fun getProfileInformation(@Path("username") username: String): Call<ProfileViewDTO>
}