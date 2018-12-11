package com.hasanatasoy.iamat.api.api_post

import com.hasanatasoy.iamat.post.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostRequest {

    @POST("post/delete")
    fun deletePost(@Body post: Post): Call<Boolean>

    @GET("post//get/{latitude}/{longtitude}")
    fun getAllPostFromLocation(@Path("latitude") latitude: Double, @Path("longtitude") longtitude: Double): Call<List<Post>>

    @GET("post/time/{latitude/{longtitude}/{time}")
    fun getAllPostFromLocationAndTime(@Path("latitude") latitude: Double, @Path("longtitude") longtitude: Double
    , @Path("time") time: String): Call<List<Post>>
}