package com.audiolly.models

import android.net.Uri

class Artist(
    val id: String,
    val biography: String,
    val thumbnail: Uri,
    val genre: String,
    val albums: List<Album>
)