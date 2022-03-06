package com.audiolly.features.ranking.music

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.audiolly.R
import com.audiolly.models.MusicTrending
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class MusicRankingAdapter(private val musics: MutableList<MusicTrending>) :
    RecyclerView.Adapter<MusicRankingItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicRankingItem {
        return MusicRankingItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.music_ranking_item, parent, false)
        )
    }

    override fun onBindViewHolder(cell: MusicRankingItem, position: Int) {
        val displayPosition = (position + 1).toString()
        fillViewData(cell, displayPosition, position)
        setListeners(cell, position)
    }

    private fun setListeners(
        cell: MusicRankingItem,
        position: Int
    ) {
        cell.itemView.setOnClickListener {
            cell.itemView
                .findNavController()
                .navigate(
                    R.id.action_tab_rankings_to_artistFragment,
                    bundleOf("artistId" to musics[position].idArtist)
                )
        }
    }

    private fun fillViewData(
        cell: MusicRankingItem,
        displayPosition: String,
        position: Int
    ) {
        cell.rank.text = displayPosition
        cell.songTitle.text = musics[position].strTrack
        cell.songArtists.text = musics[position].strArtist
        Glide.with(cell.thumbnail.context)
            .load(musics[position].strTrackThumb)
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .placeholder(R.drawable.ic_placeholder_album)
            .into(cell.thumbnail)
    }

    override fun getItemCount(): Int {
        return musics.size
    }
}

