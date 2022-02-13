package com.audiolly.api

import com.audiolly.api.response.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TheAudioDBNetworkManager {
    private val retrofit: AudioDBAPI = Retrofit.Builder()
        .baseUrl("https://theaudiodb.com/api/v1/json/523532/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(AudioDBAPI::class.java)

    suspend fun getAlbumRankingAsync(): AlbumsRankingResponse {
        return retrofit.getAlbumRankingAsync().await()
    }

    suspend fun getMusicRankingAsync(): MusicsRankingResponse {
        return retrofit.getMusicRankingAsync().await()
    }

    suspend fun getAlbumDataAsync(albumId: String): AlbumResponse {
        return retrofit.getAlbumDataAsync(albumId).await()
    }

    suspend fun getAlbumMusicsAsync(albumId: String): MusicResponse {
        return retrofit.getAlbumMusicsAsync(albumId).await()
    }

    suspend fun getMusicDataAsync(musicId: String): MusicResponse {
        return retrofit.getMusicDataAsync(musicId).await()
    }

    suspend fun getArtistAlbumsAsync(artistId: String): AlbumResponse {
        return retrofit.getArtistAlbumsAsync(artistId).await()
    }

    suspend fun getArtistDataAsync(artistId: String): ArtistResponse {
        return retrofit.getArtistDataAsync(artistId).await()
    }

    suspend fun getArtistTopMusic(musicBrainzId: String): MusicResponse {
        return retrofit.getArtistTopMusic(musicBrainzId).await()
    }
}