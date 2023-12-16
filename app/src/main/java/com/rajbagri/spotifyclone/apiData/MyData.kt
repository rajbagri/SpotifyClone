package com.rajbagri.spotifyclone.apiData

import com.rajbagri.spotifyclone.apiData.Data

data class MyData(
    val `data`: List<Data>,
    val next: String,
    val total: Int
)