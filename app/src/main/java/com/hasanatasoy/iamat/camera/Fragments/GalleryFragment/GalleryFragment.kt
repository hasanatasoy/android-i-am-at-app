package com.hasanatasoy.iamat.camera.Fragments.GalleryFragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.hasanatasoy.iamat.BaseView
import com.hasanatasoy.iamat.R
import com.hasanatasoy.iamat.camera.BaseRecyclerAdepter
import com.hasanatasoy.iamat.camera.PhotoEdit.ImpPhotoEdit
import kotlinx.android.synthetic.main.camera_fragment_gallery.view.*
import java.io.Serializable

class GalleryFragment : Fragment() {


    private lateinit var baseRecyclerAdepter: BaseRecyclerAdepter
    private val baUrl = "https://scontent-mxp1-1.cdninstagram.com/vp/4fe41ef6073398b971a1e29b3a56bfec/5C8B6532/t51" + ".2885-19/s150x150/34090102_1480775802067695_1992576044627918848_n.jpg"
    private val taUrl = "https://scontent-mxp1-1.cdninstagram.com/vp/8bdb436f0283fb95b9e13420cda9051a/5C925EB0/t51.2885-" + "19/s150x150/43913993_401541693715175_2903520101095440384_n.jpg"
    private val ssUrl = "https://scontent-mxp1-1.xx.fbcdn.net/v/t1.0-1/p160x160/14358875_1268600166524111_373828231972958750" + "8_n.jpg?_nc_cat=111&_nc_ht=scontent-mxp1-1.xx&oh=24a43723ff32bd9641db5c1875756afa&oe=5CAE867C"
    private val imgList: List<String>
        get() {
            val imgList = ArrayList<String>()
            imgList.add(baUrl)
            imgList.add(taUrl)
            imgList.add(ssUrl)
            return imgList
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.camera_fragment_gallery, container, false)
        //String List will be bitmap list and program will get all images from gallery to list
        baseRecyclerAdepter = BaseRecyclerAdepter("gallery", imgList, inflater.context,null)
        view.camera_gallery_recyclerview.adapter = baseRecyclerAdepter
        var layoutManager = GridLayoutManager(inflater.context,2)
        layoutManager.orientation = GridLayoutManager.VERTICAL
        view.camera_gallery_recyclerview.layoutManager = layoutManager
        return view
    }
}