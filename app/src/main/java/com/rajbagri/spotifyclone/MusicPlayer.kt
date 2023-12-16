package com.rajbagri.spotifyclone

import android.graphics.RenderEffect
import android.graphics.Shader
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import com.rajbagri.spotifyclone.upperTypeSetup.Music
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import java.util.concurrent.TimeUnit

class MusicPlayer : AppCompatActivity() {
    private lateinit var cardView: CardView
    private lateinit var albumImage: ImageView
    private lateinit var artistImage: ImageView
    private lateinit var playButton: ImageView
    private lateinit var pauseButton: ImageView
    private lateinit var nextButton: ImageView
    private lateinit var previousButton: ImageView
    private lateinit var shuffleButton: ImageView
    private lateinit var repeatButton: ImageView
    private lateinit var songName: TextView
    private lateinit var artistName: TextView
    private lateinit var musicUrl: String
    private lateinit var imageUrl: String
    private lateinit var albumImages: String
    private lateinit var picasso: Picasso
    private lateinit var startTimer: TextView
    private lateinit var runnable: Runnable
    private lateinit var seekBar: SeekBar
    private lateinit var endTime: TextView
    companion object {
         var mediaPlayer: MediaPlayer? = null
        var curPos: Int? = null
    }


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)
        initialize()


        val musicTitle = intent.getStringExtra("MusicTitle").toString()
        musicUrl = intent.getStringExtra("musicUrl").toString()
        imageUrl = intent.getStringExtra("imageUrl").toString()
        curPos = intent.getIntExtra("currentPosition", 0)
        albumImages = intent.getStringExtra("albumImage").toString()
        songName.text = musicTitle
        val curArtistName = intent.getStringExtra("artistName").toString()
        artistName.text = curArtistName


        picasso = Picasso.Builder(this)
            .indicatorsEnabled(true) // Enable debugging indicators
            .loggingEnabled(true)    // Enable logging
            .downloader(OkHttp3Downloader(this)) // Use OkHttp3Downloader for caching
            .build()

        picasso.load(albumImages).into(artistImage)
        picasso.load(albumImages).into(albumImage)
        albumImage.setRenderEffect(RenderEffect.createBlurEffect(30F, 30F, Shader.TileMode.MIRROR))

        playMusic()
        PauseMusic()
        playButton.visibility = View.INVISIBLE
        initializeLayout()


        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {



            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Called when the user starts dragging the SeekBar
                mediaPlayer!!.seekTo(seekBar.progress)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                mediaPlayer!!.seekTo(seekBar.progress)
            }
        })

        seekBarSetUp()
        mediaPlayer!!.setOnCompletionListener {

            playButton.visibility = View.VISIBLE
            pauseButton.visibility  = View.INVISIBLE
        }


    }



    @RequiresApi(Build.VERSION_CODES.S)
    private fun initializeLayout() {

        when(intent.getStringExtra("class")){
            "NowPlaying" ->{
                val sName = intent.getStringExtra("ganaName")
                val arName = intent.getStringExtra("artName")
                val albumImaging = intent.getStringExtra("albImage")

                picasso.load(albumImaging).into(artistImage)
                picasso.load(albumImaging).into(albumImage)
                albumImage.setRenderEffect(RenderEffect.createBlurEffect(30F, 30F, Shader.TileMode.MIRROR))

                songName.text = sName
                artistName.text = arName
            }
            "DataAdapter" -> {

                if(mediaPlayer == null) mediaPlayer = MediaPlayer()
                mediaPlayer!!.reset()

                mediaPlayer!!.setDataSource(this, musicUrl.toUri())
                mediaPlayer!!.prepare()
                mediaPlayer!!.start()


                mediaPlayer!!.setOnPreparedListener {
                    // Set SeekBar max value after the media player is prepared
                    seekBar.max = mediaPlayer!!.duration
                }


            }

        }
    }


    private fun PauseMusic() {
        pauseButton.setOnClickListener {
            playButton.visibility = View.VISIBLE
            pauseButton.visibility = View.INVISIBLE
            if(MusicPlayer.mediaPlayer!!.isPlaying == true){
                MusicPlayer.mediaPlayer!!.pause()
            }
        }
    }

    private fun playMusic() {
        playButton.setOnClickListener {
            pauseButton.visibility = View.VISIBLE
            playButton.visibility = View.INVISIBLE
            if(mediaPlayer!!.isPlaying == false){
                mediaPlayer!!.start()
            }
        }
    }

    fun seekBarSetUp(){
        runnable = Runnable {

            MusicPlayer.mediaPlayer?.let {

                seekBar.progress = it.currentPosition
                seekBar.progress = it.currentPosition
                startTimer.text = TimeUnit.MILLISECONDS.toSeconds(it.currentPosition.toLong()).toString()
                endTime.text = TimeUnit.MILLISECONDS.toSeconds(it.duration.toLong()).toString()

                Handler(Looper.getMainLooper()).postDelayed(runnable, 10)
            }
        }


        Handler(Looper.getMainLooper()).postDelayed(runnable, 0)
    }


    private fun initialize(){
        cardView = findViewById(R.id.card_view_artist)
        albumImage = findViewById(R.id.music_player_album_image_blur)
        artistImage = findViewById(R.id.image_view_artist_image)
        playButton = findViewById(R.id.image_view_play)
        pauseButton = findViewById(R.id.image_view_pause)
        nextButton = findViewById(R.id.image_view_next)
        previousButton = findViewById(R.id.image_view_previous)
        shuffleButton = findViewById(R.id.image_view_shuffle)
        repeatButton = findViewById(R.id.image_view_repeat)
        songName = findViewById(R.id.text_view_song_name)
        artistName = findViewById(R.id.text_view_artist_name)
        startTimer = findViewById(R.id.text_view_start_timer)
        seekBar = findViewById(R.id.seekBar)
        endTime = findViewById(R.id.text_view_end_timer)
    }

    private fun startUpdatingSeekBar() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                seekBar.max = it.duration
                runnable = Runnable {
                    val time = it.currentPosition / 1000
                    startTimer.text = "0:$time"
                    endTime.text = (it.duration / 1000).toString()

                    seekBar.progress = it.currentPosition

                    Handler(Looper.getMainLooper()).postDelayed(runnable, 200)
                }
                Handler(Looper.getMainLooper()).postDelayed(runnable, 0)
            }
        }
    }

}


