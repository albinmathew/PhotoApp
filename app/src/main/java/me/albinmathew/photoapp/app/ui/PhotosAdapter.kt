package me.albinmathew.photoapp.app.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_photos.view.*
import me.albinmathew.photoapp.R
import me.albinmathew.photoapp.app.api.ImageData

class PhotosAdapter(private var items: List<ImageData>)
    : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_photos, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ImageData) = with(itemView) {
            val farm = item.farm
            val server = item.server
            val id = item.id
            val secret = item.secret
            val imageUrl = "http://farm$farm.static.flickr.com/$server/${id}_$secret.jpg"
            Glide.with(context).load(imageUrl).into(imageView)
        }
    }

}