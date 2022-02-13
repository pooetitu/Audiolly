package com.audiolly.api

import com.audiolly.api.response.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface AudioDBAPI {

    @GET("trending.php?country=us&type=itunes&format=albums")
    fun getAlbumRankingAsync(): Deferred<AlbumsRankingResponse>

    @GET("trending.php?country=us&type=itunes&format=singles")
    fun getMusicRankingAsync(): Deferred<MusicsRankingResponse>

    @GET("track.php")
    fun getAlbumMusicsAsync(@Query("m") albumId: String): Deferred<MusicResponse>

    @GET("track.php")
    fun getMusicDataAsync(@Query("h") musicId: String): Deferred<MusicResponse>

    @GET("album.php")
    fun getArtistAlbumsAsync(@Query("i") artistId: String): Deferred<AlbumResponse>

    @GET("album.php")
    fun getAlbumDataAsync(@Query("m") albumId: String): Deferred<AlbumResponse>

    @GET("artist.php")
    fun getArtistDataAsync(@Query("i") artistId: String): Deferred<ArtistResponse>

    @GET("track-top10-mb.php")
    fun getArtistTopMusic(@Query("s") musicBrainzId: String): Deferred<MusicResponse>
}