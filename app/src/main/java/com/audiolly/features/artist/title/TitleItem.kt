package com.audiolly.features.artist.title

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.music_item.view.*

class TitleItem(v: View) : RecyclerView.ViewHolder(v) {
    val position: TextView = v.position
    val titleName: TextView = v.title_name
}
