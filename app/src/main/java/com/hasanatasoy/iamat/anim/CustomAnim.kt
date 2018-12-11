package com.hasanatasoy.iamat.anim

import android.support.v7.app.AppCompatActivity
import com.hasanatasoy.iamat.R

object CustomAnim {

    fun animToRight(activity: AppCompatActivity) {
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun animToLeft(activity: AppCompatActivity) {
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}