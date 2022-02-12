package com.audiolly.models

data class Album (
    val idAlbum: String,
    val idArtist: String,
    val strAlbum: String,
    val strArtist: String,
    val intYearReleased: Int,
    val strGenre: String,
    val strAlbumThumb: String,
    val strDescriptionEN: String,
    val intScore: Int,
    val intScoreVotes: Int,
)