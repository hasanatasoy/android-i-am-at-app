package com.hasanatasoy.iamat.api.api_post

import android.util.Log
import android.widget.Toast
import com.hasanatasoy.iamat.api.NetworkService
import com.hasanatasoy.iamat.post.Post
import com.hasanatasoy.iamat.post.PostView
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class PostRequestService(var view: PostView) {

    val request = NetworkService.getRequestApiPost()


    fun deletePost(post: Post){
        request.deletePost(post).enqueue(object: Callback, retrofit2.Callback<Boolean>{
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                view.showError("Gönderi Silinemedi")
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                response.body()?. let {
                    if(it)
                        //deletepost
                    else
                        view.showError("Gönderi Silinemedi")
                }
            }

        })
    }

    fun getAllPostFromLocation(latitude: Double, longtitude: Double){
        request.getAllPostFromLocation(latitude,longtitude).enqueue(object: Callback, retrofit2.Callback<List<Post>>{
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                view.showError("Bağlantı hatası")
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                response.body()?. let {
                    view.setAllPost(it)
                }
            }

        })
    }
    fun getAllPostFromLocationAndTime(latitude: Double, longtitude: Double, time: String){
        request.getAllPostFromLocationAndTime(latitude,longtitude,time).enqueue(object: Callback, retrofit2.Callback<List<Post>>{
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                view.showError("Bağlantı hatası")
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                response.body()?.let {
                    view.showSuccess("Başarı ile postlar çekildi")
                    view.setAllPostAfterReflesh(it)
                }
            }

        })
    }
}