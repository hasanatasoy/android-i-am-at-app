package com.hasanatasoy.iamat.camera.PhotoEdit

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.graphics.BitmapCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.hasanatasoy.iamat.R
import com.hasanatasoy.iamat.anim.CustomAnim
import com.hasanatasoy.iamat.camera.BaseRecyclerAdepter
import com.hasanatasoy.iamat.camera.CheckPage
import com.hasanatasoy.iamat.post.ImpPostView
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.camera_photo_edit_view.*
import java.io.ByteArrayOutputStream

class ImpPhotoEdit : AppCompatActivity(), PhotoEdit {


    private lateinit var baseRecyclerAdepter: BaseRecyclerAdepter
    private lateinit var photoEditPresenter: PhotoEditPresenter
    private lateinit var bitmap: Bitmap
    private var latitude: Double? = null
    private var longtitude: Double? = null
    private var permission = arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION)
    private lateinit var locationManager: LocationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera_photo_edit_view)
        photoEditPresenter = PhotoEditPresenter(this)
        if(CheckPage.checkPageState){
            var url = intent?.extras?.getString("string")
            Glide.with(this).load(url).into(camera_photo_edit_photo)
            bitmap = (camera_photo_edit_photo.drawable as BitmapDrawable).bitmap
            camera_photo_edit_photo.setImageBitmap(bitmap)
        }
        else{
            var btmp = BitmapFactory.decodeByteArray(BitmapTransaction.bitmapByteArray,0,BitmapTransaction.bitmapByteArray!!.size)
            var matrix = rotateImage(btmp,90.0f)
            bitmap = Bitmap.createBitmap(btmp,0,0,btmp.width, btmp.height,matrix,true)
            camera_photo_edit_photo.setImageBitmap(bitmap)
        }
        var denemeList = ArrayList<String>()
        denemeList.add("red")
        denemeList.add("blue")
        denemeList.add("green")
        denemeList.add("yellow")
        denemeList.add("grey")
        baseRecyclerAdepter = BaseRecyclerAdepter("photoEdit", denemeList, this,this)
        camera_photo_edit_recyclerview.adapter = baseRecyclerAdepter
        var manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        camera_photo_edit_recyclerview.layoutManager = manager
        camera_photo_edit_photo_Done.isClickable = true
        camera_photo_edit_photo_Done.setOnClickListener {
            it.isClickable = false
            var newBitmap = Bitmap.createScaledBitmap(bitmap,bitmap.width/6,bitmap.height/6,false)
            var byteArrayOutputStream = ByteArrayOutputStream()
            newBitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream)
            var byteArray = byteArrayOutputStream.toByteArray()
            var imageString = android.util.Base64.encodeToString(byteArray,android.util.Base64.DEFAULT)
            photoEditPresenter.addNewPost(imageString,latitude!!, longtitude!!)
        }
        //location
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkPermission(permission))
                getLocation()
            else
                requestPermissions(permission,10)
        }
    }
    @SuppressLint("MissingPermission")
    fun getLocation(){
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0F,object: LocationListener {
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

    override fun goPostScreen(){
        var postIntent = Intent(this, ImpPostView::class.java)
        startActivity(postIntent)
        CustomAnim.animToLeft(this)
    }

    fun rotateImage(bitmap: Bitmap, degree: Float): Matrix{
        var matrix = Matrix()
        matrix.postRotate(degree)
        return matrix
    }

    override fun showSuccess(message: String) {
        Toasty.success(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String) {
        Toasty.error(this,message,Toast.LENGTH_LONG).show()
    }

}