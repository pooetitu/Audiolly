package com.audiolly.models

class Album(
    val id: String,
    val artist: Artist,
    val musics: List<Music>,
    val title: String,
    val description: String,
    val score: Float,
    val scoreVotes: Int
)