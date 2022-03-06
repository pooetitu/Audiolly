package com.audiolly.features.artist.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.audiolly.R
import com.audiolly.models.Album
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class AlbumAdapter(private val albums: MutableList<Album>) : RecyclerView.Adapter<AlbumItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumItem {
        return AlbumItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.album_item, parent, false)
        )
    }

    override fun onBindViewHolder(cell: AlbumItem, position: Int) {
        cell.itemView.setOnClickListener {
            cell.itemView
                .findNavController()
                .navigate(
                    R.id.action_artistFragment_to_albumFragment,
                    bundleOf("albumId" to albums[position].idAlbum)
                )
        }
        cell.albumTitle.text = albums[position].strAlbum
        cell.subtitle.text = albums[position].intYearReleased.toString()
        Glide.with(cell.thumbnail.context)
            .load(albums[position].strAlbumThumb)
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .placeholder(R.drawable.ic_placeholder_album)
            .into(cell.thumbnail)
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}