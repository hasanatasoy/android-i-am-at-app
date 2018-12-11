package com.hasanatasoy.iamat.camera.Fragments

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hasanatasoy.iamat.R
import com.hasanatasoy.iamat.camera.Fragments.GalleryFragment.GalleryFragment
import com.hasanatasoy.iamat.camera.Fragments.PhotoFragment.PhotoFragment

class FragmentAdepter(var fm: FragmentManager, val context: Context) : FragmentPagerAdapter(fm) {

    override fun getItem(index: Int): Fragment {
        when(index){
            0 -> return GalleryFragment()
            else -> return PhotoFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> context.getString(R.string.tabItemGallery)
            else -> context.getString(R.string.tabItemPhoto)
        }
    }
}