package com.audiolly.features.ranking.album

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.album_ranking_item.view.*


class AlbumItem(v: View) : RecyclerView.ViewHolder(v) {
    val rank: TextView = v.rank
    val thumbnail: ImageView = v.thumbnail
    val albumTitle: TextView = v.album_title
    val albumArtist: TextView = v.album_artist
    val rate: TextView = v.rate
    val reviewCount: TextView = v.review_count
}
