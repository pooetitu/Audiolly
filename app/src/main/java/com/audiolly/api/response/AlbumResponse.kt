package com.audiolly.api.response

import com.audiolly.models.Album
import com.google.gson.annotations.SerializedName

data class AlbumResponse(@SerializedName("album") val albums: MutableList<Album>?)