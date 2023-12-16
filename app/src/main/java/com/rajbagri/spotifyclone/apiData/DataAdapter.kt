package com.rajbagri.spotifyclone.apiData

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.rajbagri.spotifyclone.MusicPlayer
import com.rajbagri.spotifyclone.R
import com.squareup.picasso.Picasso

class DataAdapter(val context: Context, val dataList: List<Data>):
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {


    class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val Image: ImageView
        val musicTitle: TextView
        val artistname: TextView
        val cardView: CardView
        init {
            Image = itemView.findViewById(R.id.music_image)
            artistname = itemView.findViewById(R.id.artist_name)
            musicTitle = itemView.findViewById(R.id.Music_name)
            cardView = itemView.findViewById(R.id.card_view_result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.data_item, parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentData = dataList[position]


        holder.musicTitle.text = currentData.title
        holder.artistname.text = "Song • " + currentData.artist.name

        if(!currentData.album.cover.isEmpty()){
            Picasso.get().load(currentData.album.cover).into(holder.Image)
        }
        else{

        }

        holder.cardView.setOnClickListener {
            val intent = Intent(context, MusicPlayer::class.java)
            intent.putExtra("MusicTitle", currentData.title)
            intent.putExtra("imageUrl", currentData.album.cover)
            intent.putExtra("artistName", currentData.artist.name)
            intent.putExtra("musicUrl", currentData.preview)
            intent.putExtra("albumImage", currentData.album.cover_big)
            intent.putExtra("currentPosition", position)
            intent.putExtra("class", "DataAdapter")



            context.startActivity(intent)
        }



//        holder.play.setOnClickListener{
//            val mediaPlayer = MediaPlayer.create(context, currentData.preview.toUri())
//            mediaPlayer.start()
//        }
//
//        holder.pause.setOnClickListener {
//            val mediaPlayer = MediaPlayer.create(context, currentData.preview.toUri())
//            mediaPlayer.pause()
//        }
    }


}