package com.audiolly.features.ranking.music

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.music_item.view.*

class MusicItem(v: View) : RecyclerView.ViewHolder(v) {
    val rank: TextView = v.rank
    val thumbnail: ImageView = v.thumbnail
    val songTitle: TextView = v.song_title
    val songArtists: TextView = v.song_artists
}
