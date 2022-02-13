package com.audiolly.models

data class MusicTrending(
    val idTrack: String,
    val strTrack: String,
    val strArtist: String,
    val idArtist: String,
    val idAlbum: String,
    val intChartPlace: Int,
    val strTrackThumb: String
)