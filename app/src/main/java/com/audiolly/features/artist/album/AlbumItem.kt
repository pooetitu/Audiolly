package com.audiolly.features.artist.album

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.album_item.view.*


class AlbumItem(v: View) : RecyclerView.ViewHolder(v) {
    val thumbnail: ImageView = v.album_thumbnail
    val albumTitle: TextView = v.album_title
    val subtitle: TextView = v.subtitle
}
