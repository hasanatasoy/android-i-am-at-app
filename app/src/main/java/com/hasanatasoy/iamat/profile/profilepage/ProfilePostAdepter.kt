package com.hasanatasoy.iamat.profile.profilepage

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hasanatasoy.iamat.R
import com.hasanatasoy.iamat.post.Post
import kotlinx.android.synthetic.main.profile_posts.view.*


class ProfilePostAdepter(
        private var posts: List<Post>?,
        val context: Context,
        val presenter: ProfilePresenter) : RecyclerView.Adapter<ProfilePostAdepter.MViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(vG: ViewGroup, i: Int): MViewHolder {
        var view = inflater.inflate(R.layout.profile_posts, vG, false)
        return MViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts!!.size
    }

    override fun onBindViewHolder(viewHolder: MViewHolder, i: Int) {
        var post = posts!![i]
        viewHolder.bindViewAndCode(post)
        viewHolder.itemView.setOnClickListener{
            presenter.setupPostOnClickListener(it)
        }
        viewHolder.itemView.setOnLongClickListener {
            presenter.setupPostOnClickListener(it)
            return@setOnLongClickListener true
        }
    }




    inner class MViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
   {
       fun bindViewAndCode(post: Post){
           Log.d(post.postImgUrl,"PROFILE VİEW")
           Glide.with(context).load(post.postImgUrl).into(itemView.profile_post)
       }
   }
}
