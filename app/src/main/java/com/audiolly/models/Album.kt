package com.audiolly.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album")
data class Album(
    @PrimaryKey val idAlbum: String,
    val idArtist: String,
    val strAlbum: String,
    val strArtist: String,
    val intYearReleased: Int,
    val strGenre: String?,
    val strAlbumThumb: String,
    val strDescriptionEN: String?,
    val strDescriptionFR: String?,
    val intScore: Float,
    val intScoreVotes: Int,
)