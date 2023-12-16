package com.rajbagri.spotifyclone.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.rajbagri.spotifyclone.R
import com.rajbagri.spotifyclone.RecentAdapter
import com.rajbagri.spotifyclone.ViewPager
import com.rajbagri.spotifyclone.databinding.FragmentHomeBinding
import com.rajbagri.spotifyclone.songData
import com.rajbagri.spotifyclone.songListAdapter
import com.rajbagri.spotifyclone.songListData
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import java.util.Arrays


class Home : Fragment() {
    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var adapter: Adapter
    private lateinit var parentAdapter: songListAdapter
    private lateinit var songList: ArrayList<songListData>
    private lateinit var picasso: Picasso



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)


        picasso = Picasso.Builder(requireContext())
            .indicatorsEnabled(true) // Enable debugging indicators
            .loggingEnabled(true)    // Enable logging
            .downloader(OkHttp3Downloader(requireContext())) // Use OkHttp3Downloader for cachin
            .build()





        adapter = ViewPager(this)
        homeBinding.viewPager.adapter = adapter as ViewPager

        tabLayout()

        val tabTitles = Arrays.asList("All", "Music", "Podcasts")

        for(i in 0..2){
            val textView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_title, null) as TextView
            textView.text = tabTitles[i]
            homeBinding.tabLayout.getTabAt(i)?.customView = textView
        }

        addDataToList()

        homeBinding.recyclerViewListSong.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        parentAdapter = songListAdapter(requireContext(), picasso, songList)
        homeBinding.recyclerViewListSong.adapter = parentAdapter

        val childItem1: ArrayList<songData> = ArrayList()

        childItem1.add(songData("https://i.scdn.co/image/ab67706f00000003264bf425106b6e158d2752d8", "Pop Shots"))
        childItem1.add(songData("https://i.pinimg.com/originals/15/45/92/154592b3c84a0cc22e949aa3be2346d4.jpg", "Cradles"))
        childItem1.add(songData("https://th.bing.com/th/id/OIP.VmeYqnQGvTQ-zpdjibOnRgHaHa?rs=1&pid=ImgDetMain", "Until I Found \nYou                "))
        childItem1.add(songData("https://images.genius.com/a378b1c370e569baa1ac7a01225319ac.640x640x1.jpg", "Today's Top \n Hits              "))
        childItem1.add(songData("https://i.scdn.co/image/ab67616d0000b273c966c2f4e08aee0442b6b8d6", "Dua Lipa"))
        childItem1.add(songData("https://beelody.com/wp-content/uploads/2020/09/Mega-Hit-Mix.jpg", "Mega Hit Mix"))
        childItem1.add(songData("https://th.bing.com/th/id/OIP.QEsf0C_--N7dTjh-rhm8OgAAAA?w=474&h=474&rs=1&pid=ImgDetMain", "We Don't Talk \nAnyMore          "))
        childItem1.add(songData("https://th.bing.com/th/id/OIP.latxuhbhF4ca2M6hz-nQ3QHaHa?rs=1&pid=ImgDetMain", "Sorry"))

        val recentAdapter = RecentAdapter(requireContext(), childItem1)
        homeBinding.recyclerViewRecent.layoutManager = GridLayoutManager(requireContext(), 2)
        homeBinding.recyclerViewRecent.adapter = recentAdapter





    }

    private fun addDataToList() {
        val childItem1: ArrayList<songData> = ArrayList()

        childItem1.add(songData("https://beelody.com/wp-content/uploads/2020/09/Mega-Hit-Mix.jpg", "Taylor Swift, Justin Bieber, Charlie Puth..."))
        childItem1.add(songData("https://i.scdn.co/image/ab67706f0000000331e9d69dc3c2fc8214814fd2", "JustBieber"))
        childItem1.add(songData("https://w0.peakpx.com/wallpaper/187/870/HD-wallpaper-justin-bieber.jpg", "JustBieber"))
        childItem1.add(songData("https://w0.peakpx.com/wallpaper/187/870/HD-wallpaper-justin-bieber.jpg", "JustBieber"))
        childItem1.add(songData("https://w0.peakpx.com/wallpaper/187/870/HD-wallpaper-justin-bieber.jpg", "JustBieber"))
        childItem1.add(songData("https://w0.peakpx.com/wallpaper/187/870/HD-wallpaper-justin-bieber.jpg", "JustBieber"))
        childItem1.add(songData("https://w0.peakpx.com/wallpaper/187/870/HD-wallpaper-justin-bieber.jpg", "JustBieber"))
        childItem1.add(songData("https://w0.peakpx.com/wallpaper/187/870/HD-wallpaper-justin-bieber.jpg", "JustBieber"))

        songList = ArrayList()
        songList.add(songListData("Today's biggest hits",childItem1, 0f, ""))

        val topMix: ArrayList<songData> = ArrayList()

        topMix.add(songData("https://seed-mix-image.spotifycdn.com/v6/img/artist/1uNFoZAHBGtllmzznpCI3s/en/large", "Taylor Swift, Justin Bieber, Charlie Puth..."))
        topMix.add(songData("https://seed-mix-image.spotifycdn.com/v6/img/artist/6M2wZ9GZgrQXHCFfjv46we/en/large", "JustBieber"))
        topMix.add(songData("https://seed-mix-image.spotifycdn.com/v6/img/desc/Chill%20Pop/en/large", "JustBieber"))
        topMix.add(songData("https://w0.peakpx.com/wallpaper/187/870/HD-wallpaper-justin-bieber.jpg", "JustBieber"))

        songList.add(songListData("Your Top Mixes", topMix, 0f, ""))


        val yourFavorite: ArrayList<songData> = ArrayList()

        yourFavorite.add(songData("https://w0.peakpx.com/wallpaper/838/499/HD-wallpaper-charlie-puth-american-singer-brown-neon-lights-music-stars-charles-otto-puth-jr-american-celebrity-fan-art-charlie-puth.jpg", "Charlie Puth"))
        yourFavorite.add(songData("https://th.bing.com/th/id/OIP.vkucnUrcVwaumimX0JnhuwAAAA?rs=1&pid=ImgDetMain", "Justin Bieber"))
        yourFavorite.add(songData("https://i.scdn.co/image/62b33d12e2b9a033cf77585f6e3d4b2c6b3a63a1", "Taylor Swift"))
        yourFavorite.add(songData("https://th.bing.com/th/id/OIP.gqBHDEBjzd1KOaTv4lN6TQHaHa?rs=1&pid=ImgDetMain", "Psy"))
        yourFavorite.add(songData("https://images.genius.com/a3a41a358a5a341ae08ba0fb2fcd5555.640x640x1.jpg", "coldPlay"))

        songList.add(songListData("Your Favorite Artists", yourFavorite, 400f, "Center"))



    }


    private fun tabLayout() {
        TabLayoutMediator(homeBinding.tabLayout, homeBinding.viewPager) { tab, position ->
            when(position){
                0 -> {
                    tab.text = "All"
                }
                1 -> {
                    tab.text = "Music"
                }
                else -> {
                    tab.text = "Podcast"
                }
            }
        }.attach()
    }

}


