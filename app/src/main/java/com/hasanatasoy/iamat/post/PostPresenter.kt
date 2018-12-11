package com.hasanatasoy.iamat.post

import android.widget.ImageView
import com.hasanatasoy.iamat.api.api_post.PostRequestService
import java.util.*

class PostPresenter(var view:PostView) {

    private val requestService = PostRequestService(view)


    fun getAllPostFromLocation(latitude: Double, longtitude: Double){
        requestService.getAllPostFromLocation(latitude,longtitude)
    }
    fun getAllPostFromLocationAndTime(latitude: Double, longtitude: Double, time: String){
        requestService.getAllPostFromLocationAndTime(latitude,longtitude,time)
    }

    fun deletePost(post: Post){
        requestService.deletePost(post)
    }
}
