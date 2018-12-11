package com.hasanatasoy.iamat.post

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.*
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast

import com.hasanatasoy.iamat.R
import com.hasanatasoy.iamat.anim.CustomAnim
import com.hasanatasoy.iamat.camera.ImpCameraView
import com.hasanatasoy.iamat.message.ImpMessageView
import com.hasanatasoy.iamat.notification.ImpNotificationView
import com.hasanatasoy.iamat.profile.profilepage.ImpProfileView
import es.dmoral.toasty.Toasty

import kotlinx.android.synthetic.main.bottom_nav_menu_view.*
import kotlinx.android.synthetic.main.post_delete.*
import kotlinx.android.synthetic.main.post_setting_view.*
import kotlinx.android.synthetic.main.post_view.*


class ImpPostView : AppCompatActivity() , PostView {
    private lateinit var locationManager: LocationManager
    private val context: Context = this
    private lateinit var postPresenter: PostPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdabter:PostAdabter
    private lateinit var postList: List<Post>
    private var latitude: Double? = null
    private var longtitude: Double? = null
    private var permission = arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_view)
        postPresenter = PostPresenter(this)
        //location
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkPermission(permission))
                getLocation()
            else
                requestPermissions(permission,10)
        }
        //latitude ve longtitude alınacak
        postPresenter.getAllPostFromLocation(latitude!!,longtitude!!)
        // BOTTOM NAV
        navigation.menu.findItem(R.id.nav_home).isChecked = true
        navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_message -> {
                    val messageIntent = Intent(context, ImpMessageView::class.java)
                    startActivity(messageIntent)
                    menuItem.isChecked = true
                    CustomAnim.animToRight(this)
                }
                R.id.nav_add -> {
                    val cameraIntent = Intent(this,ImpCameraView::class.java)
                    startActivity(cameraIntent)
                    menuItem.isChecked = true
                    CustomAnim.animToRight(this)
                }
                R.id.nav_notification -> {
                    val notificationIntent = Intent(context, ImpNotificationView::class.java)
                    startActivity(notificationIntent)
                    menuItem.isChecked = true
                    CustomAnim.animToRight(this)
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
        post_swipe.setOnRefreshListener {
            refreshItems()
        }
    }

    private fun refreshItems() {
        // Load items
        // ...

        // Load complete
        onItemsLoadComplete()
    }

    private fun onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        post_swipe.isRefreshing = false
    }

    @SuppressLint("MissingPermission")
    fun getLocation(){
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0F,object: LocationListener{
            override fun onLocationChanged(p0: Location?) {
            }

            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
            }

            override fun onProviderEnabled(p0: String?) {
            }

            override fun onProviderDisabled(p0: String?) {
            }

        })
        var location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        latitude = location.latitude
        longtitude = location.longitude
    }

    fun checkPermission(permission: Array<String>): Boolean{
        var allSuccess = true
        for(i in permission.indices){
            if(checkCallingOrSelfPermission(permission[i]) == PackageManager.PERMISSION_DENIED)
                allSuccess = false
        }
        return allSuccess
    }

    override fun openSettings(post: Post,index: Int, itemView: View) {
        val dialog = Dialog(context)
        if(/*post.username == CurrentUsername.username*/true){
            dialog.setContentView(R.layout.post_setting_view)
            dialog.post_settings_delete.setOnClickListener {
                dialog.dismiss()
                dialog.setContentView(R.layout.post_delete)
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                dialog.post_settings_delete_deletePost.setOnClickListener {
                    dialog.dismiss()
                    deletePost(index)
                }
                dialog.post_settings_delete_dontDeletePost.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.show()
            }
        }
        else
            dialog.setContentView(R.layout.post_report)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.show()
    }

    fun deletePost(index: Int) {
        postAdabter.removeItem(index)
        checkHaveAnyPost()
    }

    override fun setAllPostAfterReflesh(postList: List<Post>) {
        if(postList.size > 0){
            for (list in postList){
                postAdabter.addItem(0,list)
            }
        }
        checkHaveAnyPost()
    }

    override fun setAllPost(postList: List<Post>) {
        this.postList = postList
        setupRecyclerViewAdepter()
        checkHaveAnyPost()
    }

    fun checkHaveAnyPost(){
        if (postAdabter.posts.size == 0)
            post_notFound.visibility = ViewGroup.VISIBLE
        else
            post_notFound.visibility = ViewGroup.GONE
    }

    override fun changeLike(imageView: ImageView,likeState: Boolean) {
        if(likeState){
            imageView.setImageResource(R.drawable.ic_like)
        }
        else{
            imageView.setImageResource(R.drawable.ic_unlike)
        }
    }

    override fun setupRecyclerViewAdepter() {
        recyclerView = findViewById(R.id.post_recyclerview)
        postAdabter = PostAdabter(postList as ArrayList<Post>, context,this)
        recyclerView.adapter = postAdabter
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
    }

    override fun showProgress() {
        post_progressBar.visibility = View.VISIBLE
        Log.d("PROGRESS BAR VISIBLE"," >>>>>>>>>>>>>>>>>>>>>>>>>>>")
    }

    override fun dismissProgress() {
        post_progressBar.visibility = View.GONE
        Log.d("PROGRESS BAR INVISIBLE"," >>>>>>>>>>>>>>>>>>>>>>>>>>>")
    }

    override fun showError(message: String) {
        Toasty.error(this,message, Toast.LENGTH_SHORT).show()
    }

    override fun showSuccess(message: String) {
        Toasty.success(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun networkError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun timeoutError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
