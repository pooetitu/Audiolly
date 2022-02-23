package com.audiolly.features.artist.title

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.audiolly.R
import com.audiolly.models.Music

class TitleAdapter(private val musics: MutableList<Music>) : RecyclerView.Adapter<TitleItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleItem {
        return TitleItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.music_item, parent, false)
        )
    }

    override fun onBindViewHolder(cell: TitleItem, position: Int) {
        val displayPosition = position + 1
        cell.position.text = displayPosition.toString()
        cell.titleName.text = musics[position].strTrack
    }

    override fun getItemCount(): Int {
        return musics.size
    }
}

