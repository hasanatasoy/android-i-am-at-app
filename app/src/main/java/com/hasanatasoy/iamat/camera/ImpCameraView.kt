package com.hasanatasoy.iamat.camera


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hasanatasoy.iamat.R
import com.hasanatasoy.iamat.camera.Fragments.FragmentAdepter
import kotlinx.android.synthetic.main.camera_view.*

class ImpCameraView : AppCompatActivity(), CameraView {
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera_view)
        val fragmentAdepter = FragmentAdepter(supportFragmentManager, context)
        camera_viewPager.adapter=fragmentAdepter
        camera_menu.setupWithViewPager(camera_viewPager)
    }
    override fun showProgress() {
    }
    override fun dismissProgress() {
    }
    override fun showError(message: String) {
    }
    override fun networkError() {
    }
    override fun timeoutError() {
    }
}