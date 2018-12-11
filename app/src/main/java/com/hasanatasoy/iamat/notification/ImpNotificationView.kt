package com.hasanatasoy.iamat.notification

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import com.hasanatasoy.iamat.R
import com.hasanatasoy.iamat.anim.CustomAnim
import com.hasanatasoy.iamat.camera.ImpCameraView
import com.hasanatasoy.iamat.message.ImpMessageView
import com.hasanatasoy.iamat.post.ImpPostView
import com.hasanatasoy.iamat.profile.profilepage.ImpProfileView
import kotlinx.android.synthetic.main.bottom_nav_menu_view.*

class ImpNotificationView : AppCompatActivity(), NotificationView {


    private val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification_view)
        // BOTTOM NAV
        navigation.menu.findItem(R.id.nav_notification).isChecked = true
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
                R.id.nav_home -> {
                    val postIntent = Intent(context, ImpPostView::class.java)
                    startActivity(postIntent)
                    menuItem.isChecked = true
                    CustomAnim.animToLeft(this)
                }
                R.id.nav_profile -> {
                    val profileInt = Intent(context, ImpProfileView::class.java)
                    startActivity(profileInt)
                    menuItem.isChecked = true
                    CustomAnim.animToRight(this)
                }
            }
            false
        }
        //
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun networkError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun timeoutError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
