package com.audiolly.api.response

import com.audiolly.models.AlbumTrending
import com.google.gson.annotations.SerializedName

data class AlbumsRankingResponse(@SerializedName("trending") val albumsRanking: MutableList<AlbumTrending>)