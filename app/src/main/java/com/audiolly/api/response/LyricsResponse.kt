package com.audiolly.api.response

import com.google.gson.annotations.SerializedName

data class LyricsResponse(@SerializedName("lyrics") val lyrics: String?)