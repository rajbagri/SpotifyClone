package com.rajbagri.spotifyclone

import android.content.Intent
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.rajbagri.spotifyclone.MusicPlayer.Companion.curPos
import com.rajbagri.spotifyclone.apiData.DataAdapter
import com.rajbagri.spotifyclone.databinding.FragmentNowPlayingBinding
import com.rajbagri.spotifyclone.searchSetup.Search
import com.rajbagri.spotifyclone.searchSetup.SearchBar
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso


class NowPlaying : Fragment() {
   private lateinit var nowPlayingBinding: FragmentNowPlayingBinding
   private lateinit var picasso: Picasso

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nowPlayingBinding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        return nowPlayingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nowPlayingBinding.root.visibility = View.INVISIBLE
        nowPlayingBinding.imageViewPlay.visibility = View.INVISIBLE



    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onResume() {
        super.onResume()
        if(MusicPlayer.mediaPlayer != null){
            nowPlayingBinding.root.visibility = View.VISIBLE

            picasso = Picasso.Builder(requireContext())
                .indicatorsEnabled(true) // Enable debugging indicators
                .loggingEnabled(true)    // Enable logging
                .downloader(OkHttp3Downloader(requireContext())) // Use OkHttp3Downloader for cachin
                .build()

            Glide.with(requireContext())
                .load(SearchBar.musicList!![curPos!!].album.cover_xl)
                .into(nowPlayingBinding.imageViewAlbumImage)

            nowPlayingBinding.textViewSongName.text = SearchBar.musicList!![curPos!!].title
            nowPlayingBinding.textViewArtistName.text = SearchBar.musicList!![curPos!!].artist.name

            Glide.with(requireContext())
                .load(SearchBar.musicList!![curPos!!].album.cover_xl)
                .into(nowPlayingBinding.imageViewBackground)

            nowPlayingBinding.imageViewBackground.setRenderEffect(RenderEffect.createBlurEffect(100F, 100F, Shader.TileMode.MIRROR))
            PauseMusic()
            playMusic()
            nowPlayingBinding.root.setOnClickListener{
                val intent = Intent(requireContext(), MusicPlayer::class.java)
                intent.putExtra("class", "NowPlaying")
                intent.putExtra("albImage", SearchBar.musicList!![curPos!!].album.cover_big)
                intent.putExtra("ganaName", SearchBar.musicList!![curPos!!].title)
                intent.putExtra("artName", SearchBar.musicList!![curPos!!].artist.name)
                startActivity(intent)
            }

        }


    }

    private fun PauseMusic() {
        nowPlayingBinding.imageViewPause.setOnClickListener {
            nowPlayingBinding.imageViewPlay.visibility = View.VISIBLE
            nowPlayingBinding.imageViewPause.visibility = View.INVISIBLE
            if(MusicPlayer.mediaPlayer!!.isPlaying == true){
                MusicPlayer.mediaPlayer!!.pause()
            }
        }
    }

    private fun playMusic() {
        nowPlayingBinding.imageViewPlay.setOnClickListener {
            nowPlayingBinding.imageViewPause.visibility = View.VISIBLE
            nowPlayingBinding.imageViewPlay.visibility = View.INVISIBLE
            if(MusicPlayer.mediaPlayer!!.isPlaying == false){
                MusicPlayer.mediaPlayer!!.start()
            }
        }
    }

}