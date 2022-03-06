package com.audiolly.api

import com.audiolly.api.response.LyricsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path


interface LyricsAPI {

    @GET("{artist}/{title}")
    fun getLyricsAsync(
        @Path(value = "artist", encoded = true) artist: String,
        @Path(value = "title", encoded = true) title: String
    ): Deferred<LyricsResponse>
}
