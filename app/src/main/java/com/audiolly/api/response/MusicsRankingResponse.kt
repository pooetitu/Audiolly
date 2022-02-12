package com.audiolly.api.response

import com.audiolly.models.Music
import com.audiolly.models.MusicTrending
import com.google.gson.annotations.SerializedName

data class MusicsRankingResponse(@SerializedName("trending") val musicsRanking: MutableList<MusicTrending>)