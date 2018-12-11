package com.hasanatasoy.iamat.camera.PhotoEdit

import com.hasanatasoy.iamat.BaseView

interface PhotoEdit {
    fun showSuccess(message: String)
    fun showError(message: String)
    fun goPostScreen()
}