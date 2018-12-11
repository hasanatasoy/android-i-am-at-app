package com.hasanatasoy.iamat.post

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hasanatasoy.iamat.R
import kotlinx.android.synthetic.main.posts.view.*

class PostAdabter(internal var posts: ArrayList<Post>, private val context: Context, private val mView: PostView) : RecyclerView.Adapter<PostAdabter.MyViewHolder>() {
    var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.posts, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        myViewHolder.setData(i)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
    fun removeItem(index: Int){
        posts.removeAt(index)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index,posts.size)
    }

    fun addItem(index: Int,post: Post){
        posts.add(index,post)
        notifyItemInserted(index)
        notifyItemRangeChanged(index,posts.size)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun setData(index: Int) {
            Log.d(posts[index].usernameImgUrl," POST ADABTER")
            Log.d(posts[index].postImgUrl," POST IMG")
            Glide.with(context).load(posts[index].usernameImgUrl).into(itemView.post_usernameImage)
            Glide.with(context).load(posts[index].postImgUrl).into(itemView.post_image)
            itemView.post_username.text = posts[index].username
            itemView.post_time.text = null
            itemView.post_settings.setOnClickListener {
                mView.openSettings(posts[adapterPosition], adapterPosition,itemView)
            }
            itemView.post_like.setOnClickListener {
                mView.changeLike(it.post_like, posts[index].likeState!!)
                posts[index].likeState = !posts[index].likeState!!
            }
            itemView.post_image.setOnClickListener{
                mView.changeLike(itemView.post_like, posts[index].likeState!!)
                posts[index].likeState = !posts[index].likeState!!
            }
            if(posts[index].likeState!!)
                itemView.post_like.setImageResource(R.drawable.ic_like)
            else
                itemView.post_like.setImageResource(R.drawable.ic_unlike)
        }
    }
}
