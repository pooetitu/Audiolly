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
import kotlinx.android.synthetic.main.lyrics_fragment.artist_name
import kotlinx.android.synthetic.main.lyrics_fragment.return_button
import kotlinx.coroutines.*
import retrofit2.HttpException

class LyricsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lyrics_fragment, parent, false)
    }

    var asyncTask: Job? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val musicId = requireArguments().getString("musicId")!!

        return_button.setOnClickListener {
            view.findNavController()
                .navigateUp()
        }
        asyncTask = GlobalScope.launch(Dispatchers.Default) {

            try {

                val music: Music =
                    TheAudioDBNetworkManager.getMusicDataAsync(musicId).musics?.get(0)!!
                val album: Album =
                    TheAudioDBNetworkManager.getAlbumDataAsync(music.idAlbum).albums?.get(0)!!
                val musicLyrics =
                    LyricsNetworkManager.getLyricsAsync(music.strArtist, music.strTrack).lyrics

                withContext(Dispatchers.Main) {
                    if (musicLyrics.isNullOrEmpty()) {
                        Toast.makeText(
                            context,
                            getText(R.string.no_lyrics_found),
                            Toast.LENGTH_LONG
                        )
                            .show()
                        view.findNavController()
                            .navigateUp()
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
            } catch (e: HttpException) {
                Toast.makeText(context, e.message(), Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
            }
        }
    }
}