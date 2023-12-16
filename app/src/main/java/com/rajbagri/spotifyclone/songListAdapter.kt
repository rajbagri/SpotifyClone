package com.rajbagri.spotifyclone

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.squareup.picasso.Picasso

class songListAdapter(val context: Context, val picasso: Picasso,  val data: ArrayList<songListData>):
    RecyclerView.Adapter<songListAdapter.songListViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): songListAdapter.songListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.parent_song_data_layout, parent, false)
        return songListViewHolder(view)
    }

    override fun onBindViewHolder(holder: songListAdapter.songListViewHolder, position: Int) {
        val currentParentItem = data[position]
        holder.recyclerViewName.text = currentParentItem.title

        holder.childRecyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)


        val adapter = SongDataAdapter(context, picasso, currentParentItem.songLayerData, currentParentItem.carddimen, currentParentItem.position)
        holder.childRecyclerView.adapter = adapter

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class songListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val childRecyclerView: RecyclerView
        val recyclerViewName: TextView

        init {
            childRecyclerView = itemView.findViewById(R.id.list_recycler_view)
            recyclerViewName = itemView.findViewById(R.id.list_view_name)
        }

    }
}