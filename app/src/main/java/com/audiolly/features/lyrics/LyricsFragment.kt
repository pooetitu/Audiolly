package com.audiolly.features.lyrics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.audiolly.R
import com.audiolly.api.LyricsNetworkManager
import com.audiolly.api.TheAudioDBNetworkManager
import com.audiolly.models.Album
import com.audiolly.models.Music
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.lyrics_fragment.*
import kotlinx.coroutines.*

class LyricsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lyrics_fragment, parent, false)
    }

    private var asyncTask: Job? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val musicId = requireArguments().getString("musicId")!!

        return_button.setOnClickListener {
            view.findNavController()
                .navigateUp()
        }
        asyncTask = GlobalScope.launch(Dispatchers.Default) {
            var music: Music? = null
            var album: Album? = null
            var musicLyrics: String? = null
            try {
                music =
                    TheAudioDBNetworkManager.getMusicDataAsync(musicId).musics?.get(0)!!
                album =
                    TheAudioDBNetworkManager.getAlbumDataAsync(music.idAlbum).albums?.get(0)
                musicLyrics =
                    LyricsNetworkManager.getLyricsAsync(music.strArtist, music.strTrack).lyrics
            } catch (e: Exception) {
            }
            withContext(Dispatchers.Main) {
                if (album == null || music == null || musicLyrics.isNullOrEmpty()) {
                    Toast.makeText(
                        context,
                        getText(R.string.no_lyrics_found),
                        Toast.LENGTH_LONG
                    ).show()
                    view.findNavController()
                        .navigateUp()
                    return@withContext
                }
                lyrics.text = musicLyrics
                song_title.text = music.strTrack
                artist_name.text = music.strArtist

                Glide.with(thumbnail_translucent)
                    .load(album.strAlbumThumb)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                    .placeholder(R.drawable.ic_placeholder_album)
                    .into(thumbnail_translucent)
                Glide.with(album_thumbnail)
                    .load(album.strAlbumThumb)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                    .placeholder(R.drawable.ic_placeholder_album)
                    .into(album_thumbnail)
            }
        }
    }
}