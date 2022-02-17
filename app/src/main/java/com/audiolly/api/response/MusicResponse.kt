package com.audiolly.api.response

import com.audiolly.models.Music
import com.google.gson.annotations.SerializedName

data class MusicResponse(@SerializedName("track") val musics: MutableList<Music>?)