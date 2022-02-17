package com.audiolly.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artist")
data class Artist(
    @PrimaryKey val idArtist: String,
    val strArtist: String,
    val strGenre: String?,
    val strBiographyEN: String?,
    val strBiographyFR: String?,
    val strCountry: String?,
    val strArtistThumb: String,
    val strMusicBrainzID: String
)