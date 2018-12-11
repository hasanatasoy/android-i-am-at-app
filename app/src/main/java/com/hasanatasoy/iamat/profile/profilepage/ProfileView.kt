package com.hasanatasoy.iamat.profile.profilepage

import android.view.View
import com.hasanatasoy.iamat.BaseView
import com.hasanatasoy.iamat.post.Post

interface ProfileView : BaseView{
    fun setupProfilePosts(profilePostList: List<Post>)
    fun setupProfileInformation(profileViewDTO: ProfileViewDTO)
    fun setupPostOnClick(view: View, postList: List<Post>)
}