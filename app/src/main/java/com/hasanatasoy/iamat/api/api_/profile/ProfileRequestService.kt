package com.hasanatasoy.iamat.api.api_.profile

import com.hasanatasoy.iamat.api.NetworkService
import com.hasanatasoy.iamat.post.Post
import com.hasanatasoy.iamat.profile.profilepage.ProfileView
import com.hasanatasoy.iamat.profile.profilepage.ProfileViewDTO
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ProfileRequestService(var view: ProfileView,var request: ProfileRequest = NetworkService.getRequestApiProfile()) {


    fun getProfileViewInformation(username: String){
        request.getProfileInformation(username).enqueue(object: Callback, retrofit2.Callback<ProfileViewDTO>{
            override fun onFailure(call: Call<ProfileViewDTO>, t: Throwable) {
                view.showError("Bağlantı hatası")
            }

            override fun onResponse(call: Call<ProfileViewDTO>, response: Response<ProfileViewDTO>) {
                response.body()?.let {
                    view.setupProfileInformation(it)
                }
            }

        })
    }
    fun getAllPost(username: String){
        var postList: List<Post>? = null
        request.
                getAllPost(username).
                enqueue(object: Callback, retrofit2.Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                postList =  response.body()
                postList?. let {
                    view.setupProfilePosts(it)
                }
            }

        })
    }
}