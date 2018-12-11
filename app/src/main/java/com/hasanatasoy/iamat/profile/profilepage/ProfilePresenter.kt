package com.hasanatasoy.iamat.profile.profilepage

import android.view.View
import com.hasanatasoy.iamat.CurrentUsername
import com.hasanatasoy.iamat.api.api_.profile.ProfileRequestService
import com.hasanatasoy.iamat.post.Post

class ProfilePresenter(var view: ProfileView){

    private val profileRequestService: ProfileRequestService = ProfileRequestService(view)
    var postList: List<Post>? = null

    fun setupProfileView(){
        //--------------------------this part coming from db in normal----------------------------------
        // -------------------------when i get profileview info from db--------------------------
        profileRequestService.getAllPost(CurrentUsername.username!!)
        profileRequestService.getProfileViewInformation(CurrentUsername.username!!)
    }

    //Calling from Adepter ( bindview )
    fun setupPostOnClickListener(v: View){
        view.setupPostOnClick(v, postList!!)
    }



}