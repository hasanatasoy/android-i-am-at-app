package com.hasanatasoy.iamat.camera.Fragments.PhotoFragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.hardware.camera2.*
import android.media.ImageReader
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.support.v4.app.Fragment
import android.util.Size
import android.view.*
import android.widget.Toast
import com.hasanatasoy.iamat.R
import com.hasanatasoy.iamat.camera.PhotoEdit.ImpPhotoEdit
import kotlinx.android.synthetic.main.camera_fragment_photo.*
import kotlinx.android.synthetic.main.camera_fragment_photo.view.*
import java.lang.Exception
import android.view.Surface.ROTATION_270
import android.view.Surface.ROTATION_180
import android.view.Surface.ROTATION_90
import android.view.Surface.ROTATION_0
import android.util.SparseIntArray
import android.hardware.camera2.CaptureRequest
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.hasanatasoy.iamat.camera.CheckPage
import com.hasanatasoy.iamat.camera.ImpCameraView
import com.hasanatasoy.iamat.camera.PhotoEdit.BitmapTransaction
import java.util.*


class PhotoFragment : Fragment(), SurfaceHolder.Callback {


    private var cameraDevice: Camera? = null
    private var surfaceHolder: SurfaceHolder? = null
    private var pictureCallBack = Camera.PictureCallback { byteArray, cam ->
        var photoEditIntent = Intent(activity,ImpPhotoEdit::class.java)
        BitmapTransaction.bitmapByteArray = byteArray
        CheckPage.checkPageState = false
        startActivity(photoEditIntent)
    }

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
        
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
        cameraDevice?.let {
            it.stopPreview()
            it.release()
            cameraDevice = null
        }

    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder?) {
        cameraDevice = Camera.open()
        cameraDevice?.setPreviewDisplay(surfaceHolder)
        cameraDevice?.setDisplayOrientation(90)
        cameraDevice?.startPreview()
    }

    override fun onPause() {
        cameraDevice?.let {
            it.stopPreview()
        }
        super.onPause()
    }

    override fun onDestroy() {
        cameraDevice?.let {
            it.stopPreview()
            it.release()
            cameraDevice = null
        }
        super.onDestroy()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.camera_fragment_photo,container,false)
        view.camera_photo_takePhotoButton.setOnClickListener {
            //take a picture and go edit page
            cameraDevice?.takePicture(null,null,null,pictureCallBack)
            //
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        surfaceHolder = camera_surfaceView.holder
        surfaceHolder?.addCallback(this)
        surfaceHolder?.setType(SurfaceHolder.SURFACE_TYPE_NORMAL)
    }

}