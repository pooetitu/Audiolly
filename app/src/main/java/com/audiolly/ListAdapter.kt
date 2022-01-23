package com.audiolly

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.music_item.view.*


class ListAdapter : RecyclerView.Adapter<ListItemCell>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemCell {
        return ListItemCell(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.music_item, parent, false)
        )
    }

    override fun onBindViewHolder(cell: ListItemCell, position: Int) {
        cell.name.text = position.toString()
    }

    override fun getItemCount(): Int {
        return 10
    }
}

class ListItemCell(v: View) : RecyclerView.ViewHolder(v) {
    val name: TextView = v.name
}
