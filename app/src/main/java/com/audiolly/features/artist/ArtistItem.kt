package com.audiolly.features.artist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.artist_item.view.*

class ArtistItem(v: View) : RecyclerView.ViewHolder(v) {
    val artistName: TextView = v.artist_title
    val thumbnail: ImageView = v.artist_thumbnail
}
