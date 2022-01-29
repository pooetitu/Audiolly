package com.audiolly.features.ranking.music

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.audiolly.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class MusicRankingAdapter : RecyclerView.Adapter<MusicRankingItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicRankingItem {
        return MusicRankingItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.music_ranking_item, parent, false)
        )
    }

    override fun onBindViewHolder(cell: MusicRankingItem, position: Int) {
        cell.itemView.setOnClickListener {
            cell.itemView
                .findNavController()
                .navigate(R.id.action_tab_rankings_to_artistFragment)
        }
        cell.rank.text = position.toString()
        cell.songTitle.text = "Gucci Gang"
        cell.songArtists.text = "Lil Pump"
        Glide.with(cell.thumbnail.context)
            .load("https://e.snmc.io/i/600/s/d537cb481e92854ec656a2e68adb13b9/9116225/stylesandcomplete-x-nathaniel-knows-x-purowuan-gucci-gang-stylesandcomplete-x-nathaniel-knows-x-purowuan-remix-Cover-Art.jpg")
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .placeholder(R.drawable.ic_placeholder_album)
            .into(cell.thumbnail)
    }

    override fun getItemCount(): Int {
        return 10
    }
}

