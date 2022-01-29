package com.audiolly.features.artist.title

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.audiolly.R

class TitleAdapter : RecyclerView.Adapter<TitleItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleItem {
        return TitleItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.music_item, parent, false)
        )
    }

    override fun onBindViewHolder(cell: TitleItem, position: Int) {
        println(position)
        cell.position.text = position.toString()
        cell.titleName.text = "Gucci Gang"
    }

    override fun getItemCount(): Int {
        return 10
    }
}

