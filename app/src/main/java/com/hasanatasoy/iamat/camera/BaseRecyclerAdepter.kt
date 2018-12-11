package com.hasanatasoy.iamat.camera

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hasanatasoy.iamat.R
import com.hasanatasoy.iamat.camera.BaseRecyclerAdepter.MyViewHolder
import com.hasanatasoy.iamat.camera.PhotoEdit.ImpPhotoEdit
import kotlinx.android.synthetic.main.camera_fragment_gallery_image.view.*
import kotlinx.android.synthetic.main.camera_photo_edit_view.*
import android.graphics.Bitmap
import com.hasanatasoy.iamat.R.mipmap.ic_launcher
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.hasanatasoy.iamat.camera.PhotoEdit.BitmapTransaction
import java.io.ByteArrayOutputStream


class BaseRecyclerAdepter(var type: String, var imageList:List<String>, var context: Context, var mView: ImpPhotoEdit?)
    : RecyclerView.Adapter<MyViewHolder>() {

    private var inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(container: ViewGroup, i: Int): MyViewHolder {
        var view = inflater.inflate(R.layout.camera_fragment_gallery_image,container,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, i: Int) {
        if(type == "gallery"){
            Glide.with(context).load(imageList[i]).into(viewHolder.view.camera_gallery_image)
            viewHolder.view.camera_gallery_image.setOnClickListener {
                var editPhotoInt = Intent(context,ImpPhotoEdit::class.java)
                var bundle = Bundle()
                bundle.putString("string",imageList[viewHolder.adapterPosition])
                editPhotoInt.putExtras(bundle)
                CheckPage.checkPageState = true
                context.startActivity(editPhotoInt)
            }
        }
        else if (type == "photoEdit"){

            when(imageList[i]){
                "red" -> viewHolder.view.camera_gallery_image.setBackgroundColor(context.resources.getColor(R.color.red))
                "blue" ->viewHolder.view.camera_gallery_image.setBackgroundColor(context.resources.getColor(R.color.blue))
                "green" -> viewHolder.view.camera_gallery_image.setBackgroundColor(context.resources.getColor(R.color.green))
                "yellow" -> viewHolder.view.camera_gallery_image.setBackgroundColor(context.resources.getColor(R.color.yellow))
                "grey" -> viewHolder.view.camera_gallery_image.setBackgroundColor(context.resources.getColor(R.color.grey))
            }
            viewHolder.view.camera_gallery_image.setOnClickListener {
                when(imageList[i]){
                    "red" -> {
                        mView?.camera_photo_edit_photo?.setColorFilter(Color.RED,PorterDuff.Mode.LIGHTEN)
                    }
                    "blue" -> {
                        mView?.camera_photo_edit_photo?.setColorFilter(Color.BLUE,PorterDuff.Mode.LIGHTEN)
                    }
                    "green" -> {
                        mView?.camera_photo_edit_photo?.setColorFilter(Color.GREEN,PorterDuff.Mode.LIGHTEN)
                    }
                    "yellow" -> {
                        mView?.camera_photo_edit_photo?.setColorFilter(Color.YELLOW,PorterDuff.Mode.LIGHTEN)
                    }
                    "gray" -> {
                        mView?.camera_photo_edit_photo?.setColorFilter(Color.GRAY,PorterDuff.Mode.LIGHTEN)
                    }
                }
            }
        }

    }


    inner class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}