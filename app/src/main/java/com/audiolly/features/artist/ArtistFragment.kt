package com.audiolly.features.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.audiolly.R
import com.audiolly.api.TheAudioDBNetworkManager
import com.audiolly.features.artist.album.AlbumAdapter
import com.audiolly.features.artist.title.TitleAdapter
import com.audiolly.storage.DatabaseManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.artist_fragment.*
import kotlinx.coroutines.*
import java.util.*

class ArtistFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.artist_fragment, parent, false)
    }
    var isFavorite: Boolean = false
    var asyncTask: Job? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = DatabaseManager(requireContext())

        return_button.setOnClickListener {
            view.findNavController()
                .navigateUp()
        }
        asyncTask = GlobalScope.launch(Dispatchers.Default) {
            val artistId = requireArguments().getString("artistId")
            val artist = TheAudioDBNetworkManager.getArtistDataAsync(artistId!!).artists?.get(0)
            val albums = TheAudioDBNetworkManager.getArtistAlbumsAsync(artistId).albums
            val musics =
                TheAudioDBNetworkManager.getArtistTopMusicAsync(artist?.strMusicBrainzID!!).musics ?: mutableListOf()
            val favoriteArtist = db.findArtistById(artistId)
            withContext(Dispatchers.Main) {
                Glide.with(artist_thumbnail.context)
                    .load(artist.strArtistThumb)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                    .placeholder(R.drawable.ic_placeholder_artist)
                    .into(artist_thumbnail)
                if (artist != null) {
                    artist_name.text = artist.strArtist
                }
                artist_location_genre.text =
                    getString(R.string.location_genre, artist.strCountry, artist.strGenre)
                albums_count.text = getString(R.string.albums_count, albums?.size)
                description.text = when (Locale.getDefault().language) {
                    "fr" -> artist.strBiographyFR
                    else -> artist.strBiographyEN
                }
                albums_list.run {
                    layoutManager = GridLayoutManager(
                        this@ArtistFragment.context,
                        3,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    adapter = AlbumAdapter(albums!!)
                }
                appreciated_titles_list.run {
                    layoutManager = LinearLayoutManager(this@ArtistFragment.context)
                    adapter = TitleAdapter(musics, R.id.action_artistFragment_to_lyricsFragment)
                }
                isFavorite = favoriteArtist != null
                if(isFavorite){
                    like_button.setImageResource(R.drawable.ic_like_merged)
                }
                like_button.setOnClickListener {
                    GlobalScope.launch(Dispatchers.Default) {
                        isFavorite = !isFavorite
                        if(isFavorite){
                            like_button.setImageResource(R.drawable.ic_like_merged)
                            db.insertArtist(artist)
                        } else {
                            like_button.setImageResource(R.drawable.ic_like_off)
                            db.deleteArtist(artist)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        asyncTask?.cancel()
    }
}