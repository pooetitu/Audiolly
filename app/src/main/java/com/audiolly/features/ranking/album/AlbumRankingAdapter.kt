package com.audiolly.features.ranking.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.audiolly.R
import com.audiolly.api.TheAudioDBNetworkManager
import com.audiolly.models.Album
import com.audiolly.models.AlbumTrending
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AlbumRankingAdapter(private val albums: MutableList<AlbumTrending>) :
    RecyclerView.Adapter<AlbumRankingItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumRankingItem {
        return AlbumRankingItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.album_ranking_item, parent, false)
        )
    }

    override fun onBindViewHolder(cell: AlbumRankingItem, position: Int) {
        GlobalScope.launch(Dispatchers.Default) {
            var album: Album? = null
            try {
                album =
                    TheAudioDBNetworkManager.getAlbumDataAsync(albums[position].idAlbum).albums?.get(
                        0
                    )
            } catch (e: Exception) {
            }
            withContext(Dispatchers.Main) {
                fillDataFromAPI(album, cell)
            }
        }
        fillViewData(position, cell)
        setListeners(cell, position)
    }

    private fun setListeners(
        cell: AlbumRankingItem,
        position: Int
    ) {
        cell.itemView.setOnClickListener {
            cell.itemView
                .findNavController()
                .navigate(
                    R.id.action_tab_rankings_to_albumFragment,
                    bundleOf("albumId" to albums[position].idAlbum)
                )
        }
    }

    private fun fillViewData(
        position: Int,
        cell: AlbumRankingItem
    ) {
        val displayedPosition = position + 1
        cell.rank.text = displayedPosition.toString()
        cell.albumTitle.text = albums[position].strAlbum
        cell.albumArtist.text = albums[position].strArtist
        Glide.with(cell.thumbnail.context)
            .load(albums[position].strAlbumThumb)
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .placeholder(R.drawable.ic_placeholder_album)
            .into(cell.thumbnail)
    }

    private fun fillDataFromAPI(
        album: Album?,
        cell: AlbumRankingItem
    ) {
        if (album == null) {
            cell.itemView.findNavController()
                .navigateUp()
            return
        }
        cell.rate.text = cell.itemView.context.getString(R.string.rate, album.intScore)
        cell.reviewCount.text = cell.itemView.context.getString(
            R.string.review_count,
            album.intScoreVotes
        )
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}