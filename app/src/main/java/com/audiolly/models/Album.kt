package com.audiolly.models

data class Album(
    val idAlbum: String,
    val idArtist: String,
    val strAlbum: String,
    val strArtist: String,
    val intYearReleased: Int,
    val strGenre: String,
    val strAlbumThumb: String,
    val strDescriptionEn: String,
    val strDescriptionFr: String,
    val intScore: Float,
    val intScoreVotes: Int,
)