package com.audiolly.features.artist.title

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.audiolly.R
import com.audiolly.models.Music

class TitleAdapter(private val musics: MutableList<Music>, private val lyricsAction: Int) : RecyclerView.Adapter<TitleItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleItem {
        return TitleItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.music_item, parent, false)
        )
    }

    override fun onBindViewHolder(cell: TitleItem, position: Int) {
        val pos = position + 1
        cell.position.text = pos.toString()
        cell.titleName.text = musics[position].strTrack
        cell.itemView.setOnClickListener {
            cell.itemView
                .findNavController()
                .navigate(
                    lyricsAction,
                    bundleOf("musicId" to musics[position].idTrack)
                )
        }
    }

    override fun getItemCount(): Int {
        return musics.size
    }
}

