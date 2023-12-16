package com.rajbagri.spotifyclone.searchSetup

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.rajbagri.spotifyclone.MusicPlayer
import com.rajbagri.spotifyclone.R
import com.squareup.picasso.Picasso

class BrowseCardAdapter(val context: Context,
                        private val cardList: ArrayList<BrowseCards>,
                        private val picasso: Picasso
)
    : RecyclerView.Adapter<BrowseCardAdapter.DataViewHolder>() {



    inner class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView
        val textView: TextView
        val cardView: CardView
        init {
            imageView = itemView.findViewById(R.id.image_view_type)
            textView = itemView.findViewById(R.id.text_view_type)
            cardView = itemView.findViewById(R.id.card_view_type)

            cardView.setOnClickListener {

            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.browse_card, parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentData = cardList[position]

        holder.textView.text = currentData.textView.toString()
        holder.cardView.setCardBackgroundColor(currentData.color)

        picasso.load(currentData.imageView)
            .fit()
            .into(holder.imageView)




    }
}


