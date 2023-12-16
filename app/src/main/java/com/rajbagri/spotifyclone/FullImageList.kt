package com.rajbagri.spotifyclone

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.rajbagri.spotifyclone.databinding.ActivityFullImageListBinding

class FullImageList : AppCompatActivity() {
    private lateinit var binding: ActivityFullImageListBinding
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFullImageListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val imageUrlLink = intent.getStringExtra("imageUrl")
        val cardNameString = intent.getStringExtra("cardName")

        binding.backgrouFullImage.setRenderEffect(RenderEffect.createBlurEffect(200F, 200F, Shader.TileMode.MIRROR))

        Glide.with(this)
            .load(imageUrlLink)
            .into(binding.fullImage)


        Glide.with(this)
            .load(imageUrlLink)
            .into(binding.backgrouFullImage)

    }
}