package com.audiolly.api.response

import com.audiolly.models.Artist
import com.google.gson.annotations.SerializedName

data class ArtistResponse(@SerializedName("track") val artists: MutableList<Artist>)