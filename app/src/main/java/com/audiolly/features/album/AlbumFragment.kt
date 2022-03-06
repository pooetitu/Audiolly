package com.audiolly.features.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.audiolly.R
import com.audiolly.api.TheAudioDBNetworkManager
import com.audiolly.features.artist.title.TitleAdapter
import com.audiolly.models.Album
import com.audiolly.models.Music
import com.audiolly.storage.DatabaseManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.album_fragment.*
import kotlinx.coroutines.*
import java.util.*

class AlbumFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.album_fragment, parent, false)
    }

    var isFavorite: Boolean = false
    private var asyncTask: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = DatabaseManager(requireContext())

        val albumId = requireArguments().getString("albumId")!!
        asyncTask = GlobalScope.launch(Dispatchers.Default) {
            var album: Album? = null
            var musics: MutableList<Music> = mutableListOf()
            var favoriteAlbum: Album? = null
            try {
                album = TheAudioDBNetworkManager.getAlbumDataAsync(albumId).albums?.get(0)
                musics = TheAudioDBNetworkManager.getAlbumMusicsAsync(albumId).musics ?: musics
                favoriteAlbum = db.findAlbumById(albumId)
            } catch (e: Exception) {
            }
            withContext(Dispatchers.Main) {
                if (album == null) {
                    view.findNavController()
                        .navigateUp()
                    return@withContext
                }
                album_name.text = album.strAlbum
                artist_name.text = album.strArtist
                description.text = when (Locale.getDefault().language) {
                    "fr" -> album.strDescriptionFR
                    else -> album.strDescriptionEN
                }
                rate.text = album.intScore.toString()
                votes_count.text = getString(R.string.review_count, album.intScoreVotes)
                song_count.text = getString(R.string.songs_count, musics.size)
                Glide.with(album_thumbnail.context)
                    .load(album.strAlbumThumb)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                    .placeholder(R.drawable.ic_placeholder_album)
                    .into(album_thumbnail)
                Glide.with(thumbnail_translucent.context)
                    .load(album.strAlbumThumb)
                    .centerCrop()
                    .placeholder(R.drawable.ic_placeholder_album)
                    .into(thumbnail_translucent)
                return_button.setOnClickListener {
                    view.findNavController()
                        .navigateUp()
                }
                titles_list.run {
                    layoutManager = LinearLayoutManager(this@AlbumFragment.context)
                    adapter = TitleAdapter(musics, R.id.action_albumFragment_to_lyricsFragment)
                }
                isFavorite = favoriteAlbum != null
                if (isFavorite) {
                    like_button.setImageResource(R.drawable.ic_like_merged)
                }
                like_button.setOnClickListener {
                    GlobalScope.launch(Dispatchers.Default) {
                        isFavorite = !isFavorite
                        if (isFavorite) {
                            like_button.setImageResource(R.drawable.ic_like_merged)
                            db.insertAlbum(album)
                        } else {
                            like_button.setImageResource(R.drawable.ic_like_off)
                            db.deleteAlbum(album)
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
