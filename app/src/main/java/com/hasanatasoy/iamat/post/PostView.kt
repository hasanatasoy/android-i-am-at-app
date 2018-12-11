package com.hasanatasoy.iamat.post

import android.view.View
import android.widget.ImageView
import com.hasanatasoy.iamat.BaseView

interface PostView : BaseView{
    fun showSuccess(message: String)
    fun openSettings(post:Post, index: Int, itemView: View)
    fun changeLike(imageView: ImageView, likeState: Boolean)
    fun setupRecyclerViewAdepter()
    fun setAllPost(postList: List<Post>)
    fun setAllPostAfterReflesh(postList: List<Post>)
}
