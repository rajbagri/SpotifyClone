package com.rajbagri.spotifyclone

import android.database.DataSetObserver
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rajbagri.spotifyclone.upperTypeSetup.All
import com.rajbagri.spotifyclone.upperTypeSetup.Music
import com.rajbagri.spotifyclone.upperTypeSetup.Podcast

class ViewPager(fragment: Fragment):
    FragmentStateAdapter(fragment), Adapter {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            1 -> All()
            2 -> Music()
            else -> Podcast()
        }
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }


}