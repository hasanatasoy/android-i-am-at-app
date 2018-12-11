package com.hasanatasoy.iamat.camera.PhotoEdit

import android.graphics.Bitmap
import com.hasanatasoy.iamat.BaseView
import com.hasanatasoy.iamat.CurrentUsername
import com.hasanatasoy.iamat.api.api_camera.CameraRequestService
import com.hasanatasoy.iamat.post.PostDTO
import java.io.ByteArrayOutputStream
import java.util.*

class PhotoEditPresenter(val view: PhotoEdit) {
    val cameraRequestService: CameraRequestService = CameraRequestService(view)

    fun addNewPost(imageString: String,latitude: Double, longtitude: Double){
        cameraRequestService.addNewPostAndCheckStatus(PostDTO(CurrentUsername.username, imageString, latitude, longtitude))
    }
}