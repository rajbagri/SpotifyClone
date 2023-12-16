package com.rajbagri.spotifyclone

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class SongDataAdapter(val context: Context, val picasso: Picasso, val data: ArrayList<songData>,
                            private val cardCornerRadius: Float, private val positions: String) :
    RecyclerView.Adapter<SongDataAdapter.SongDataViewHolder>() {

    class SongDataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView
        val textView: TextView
        val cardRecyclerView: CardView

        init {
            image = itemView.findViewById(R.id.image_view_recycler_home)
            textView = itemView.findViewById(R.id.text_view_artist_names)
            cardRecyclerView = itemView.findViewById(R.id.card_view_recycler_home)
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SongDataAdapter.SongDataViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.home_recycler_view_card, parent, false)
        return SongDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongDataAdapter.SongDataViewHolder, position: Int) {

        val currentData = data[position]

        Glide.with(context)
            .load(currentData.imageUrl)
            .into(holder.image)

        holder.textView.text = currentData.textView

        if(positions == "Center") holder.textView.gravity = Gravity.CENTER_HORIZONTAL

        holder.cardRecyclerView.radius = cardCornerRadius

        holder.cardRecyclerView.setOnClickListener {
            val intent = Intent(context, FullImageList::class.java)
            intent.putExtra("imageUrl", currentData.imageUrl)
            intent.putExtra("cardName", currentData.textView)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }



}