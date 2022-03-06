package com.audiolly.api

import com.audiolly.api.response.LyricsResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LyricsNetworkManager {

    private val retrofit: LyricsAPI = Retrofit.Builder()
        .baseUrl("https://api.lyrics.ovh/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(LyricsAPI::class.java)

    suspend fun getLyricsAsync(artist: String, title: String): LyricsResponse {
        return retrofit.getLyricsAsync(artist, title).await()
    }
}