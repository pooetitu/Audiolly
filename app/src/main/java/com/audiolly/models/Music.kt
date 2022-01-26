package com.audiolly.models

import android.net.Uri

class Music(
    val id: String,
    val artist: List<Artist>,
    val title: String,
    val album: Album,
    val thumbnail: Uri,
)