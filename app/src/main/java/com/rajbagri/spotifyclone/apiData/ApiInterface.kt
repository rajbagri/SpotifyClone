package com.rajbagri.spotifyclone.apiData
import androidx.media3.ui.BuildConfig
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiInterface {
    @Headers("X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(
        @Query("q") query: String,
        @Header("X-RapidAPI-Key") apiKey: String
    ): Call<MyData>

}

