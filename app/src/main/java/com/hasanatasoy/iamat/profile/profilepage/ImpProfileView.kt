package com.hasanatasoy.iamat.profile.profilepage

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.hasanatasoy.iamat.CurrentUsername
import com.hasanatasoy.iamat.R

import com.hasanatasoy.iamat.anim.CustomAnim
import com.hasanatasoy.iamat.camera.ImpCameraView
import com.hasanatasoy.iamat.message.ImpMessageView
import com.hasanatasoy.iamat.notification.ImpNotificationView
import com.hasanatasoy.iamat.post.Post
import com.hasanatasoy.iamat.post.ImpPostView
import com.hasanatasoy.iamat.profile.profileeditpage.ImpEditProfile
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.bottom_nav_menu_view.*
import kotlinx.android.synthetic.main.posts.*
import kotlinx.android.synthetic.main.profile_view.*


class ImpProfileView : AppCompatActivity() , ProfileView {


    private lateinit var profilePresenter: ProfilePresenter
    private val context: Context = this
    private lateinit var profileViewDTO: ProfileViewDTO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_view)
        profilePresenter = ProfilePresenter(this)
        // ----------------BOTTOM NAV----------------------
        navigation.menu.findItem(R.id.nav_profile).isChecked = true
        navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_message -> {
                    val messageIntent = Intent(context, ImpMessageView::class.java)
                    startActivity(messageIntent)
                    menuItem.isChecked = true
                    CustomAnim.animToLeft(this)
                }
                R.id.nav_add -> {
                    val cameraIntent = Intent(this,ImpCameraView::class.java)
                    startActivity(cameraIntent)
                    menuItem.isChecked = true
                    CustomAnim.animToLeft(this)
                }
                R.id.nav_notification -> {
                    val notificationIntent = Intent(context, ImpNotificationView::class.java)
                    startActivity(notificationIntent)
                    menuItem.isChecked = true
                    CustomAnim.animToLeft(this)
                }
                R.id.nav_home -> {
                    val postIntent = Intent(context, ImpPostView::class.java)
                    startActivity(postIntent)
                    menuItem.isChecked = true
                    CustomAnim.animToLeft(this)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
        //-----------------------------------------------------
        profilePresenter.setupProfileView()
        profile_settings.setOnClickListener {
            var editProfileIntent = Intent(this,ImpEditProfile::class.java)
            startActivity(editProfileIntent)
            CustomAnim.animToRight(this)
        }
    }

    override fun setupProfilePosts(profilePostList: List<Post>) {
        // --------------SET ADEPTER AND POSTS-------------------
        val profilePostAdepter = ProfilePostAdepter(profilePostList, context, profilePresenter)
        profile_post_list.adapter = profilePostAdepter
        val gridLayoutManager = GridLayoutManager(context,3)
        profile_post_list.layoutManager = gridLayoutManager
        ////
        profilePresenter.postList = profilePostList
    }

    override fun setupProfileInformation(profileViewDTO: ProfileViewDTO) {
        this.profileViewDTO = profileViewDTO
        profile_username.text = "@${profileViewDTO.username}"
        Glide.with(this).load(profileViewDTO.userPP).into(profile_PP)
        profile_name.text = profileViewDTO.name
        profile_depcription.text = profileViewDTO.description
        profile_biodescription.text = profileViewDTO.bio
        profile_likesCount.text = "${profileViewDTO.likeCount}"
        profile_photosCount.text = "${profileViewDTO.photoCount}"
    }

    override fun setupPostOnClick(view:View, postList: List<Post>){
        val i = profile_post_list.getChildAdapterPosition(view)
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.posts)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        Glide.with(context).load(postList[i].usernameImgUrl).into(dialog.post_usernameImage)
        dialog.post_username.text = postList[i].username
        dialog.post_time.text = null
        if (postList[i].likeState!!)
            dialog.post_like.setImageResource(R.drawable.ic_like)
        else
            dialog.post_like.setImageResource(R.drawable.ic_unlike)
        Glide.with(context).load(postList[i].postImgUrl).into(dialog.post_image)
        dialog.post_like.setOnClickListener {
            if (postList[i].likeState!!)
                dialog.post_like.setImageResource(R.drawable.ic_unlike)
            else
                dialog.post_like.setImageResource(R.drawable.ic_like)
            postList[i].likeState = !postList[i].likeState!!
        }
        dialog.show()
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String) {
        Toasty.error(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun networkError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun timeoutError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
