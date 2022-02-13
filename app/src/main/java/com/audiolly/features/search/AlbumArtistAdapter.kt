package com.audiolly.features.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.audiolly.R
import com.audiolly.features.artist.ArtistItem
import com.audiolly.features.artist.album.AlbumItem
import com.audiolly.models.Album
import com.audiolly.models.Artist
import com.audiolly.models.Section
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class AlbumArtistAdapter(val objectsList: MutableList<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val SECTION = 0
    val ARTIST = 1
    val ALBUM = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            SECTION -> SectionItem(LayoutInflater.from(parent.context)
                .inflate(R.layout.section_item, parent, false))
            ARTIST -> ArtistItem(LayoutInflater.from(parent.context)
                .inflate(R.layout.artist_item, parent, false))
            else -> AlbumItem(LayoutInflater.from(parent.context)
                .inflate(R.layout.album_item, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ArtistItem){
            holder.artistName.text = (objectsList[position] as Artist).strArtist
            Glide.with(holder.thumbnail.context)
                .load((objectsList[position] as Artist).strArtistThumb)
                .centerCrop()
                .circleCrop()
                .placeholder(R.drawable.ic_placeholder_artist)
                .into(holder.thumbnail)
            holder.itemView.setOnClickListener {
                holder.itemView
                    .findNavController()
                    .navigate(
                        R.id.action_tab_search_to_artistFragment,
                        bundleOf("artistId" to (objectsList[position] as Artist).idArtist)
                    )
            }
        }
        else if(holder is AlbumItem){
            holder.albumTitle.text = (objectsList[position] as Album).strAlbum
            holder.subtitle.text = (objectsList[position] as Album).strArtist
            Glide.with(holder.thumbnail.context)
                .load((objectsList[position] as Album).strAlbumThumb)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                .placeholder(R.drawable.ic_placeholder_album)
                .into(holder.thumbnail)
            holder.itemView.setOnClickListener {
                holder.itemView
                    .findNavController()
                    .navigate(
                        R.id.action_tab_search_to_albumFragment,
                        bundleOf("albumId" to (objectsList[position] as Album).idAlbum)
                    )
            }
        }
        else if (holder is SectionItem) {
            holder.title.text = (objectsList[position] as Section).title

        }
    }

    override fun getItemCount(): Int {
        return objectsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(objectsList[position] is Section) {
            SECTION
        }
        else if(objectsList[position] is Artist) {
            ARTIST
        }
        else {
            ALBUM
        }
    }
}
