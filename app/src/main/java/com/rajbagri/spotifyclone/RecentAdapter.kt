package com.rajbagri.spotifyclone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecentAdapter(val context: Context, private val dataList: ArrayList<songData>): RecyclerView.Adapter<RecentAdapter.RecentViewHolder>() {
    class RecentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val recentImageView: ImageView
        val recentSongName: TextView
        init {
            recentImageView = itemView.findViewById(R.id.image_view_album_image)
            recentSongName = itemView.findViewById(R.id.text_view_song_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recent_layout, parent, false)
        return RecentAdapter.RecentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        val currentData = dataList[position]

        Glide.with(context)
            .load(currentData.imageUrl)
            .into(holder.recentImageView)

        holder.recentSongName.text = currentData.textView

    }

}