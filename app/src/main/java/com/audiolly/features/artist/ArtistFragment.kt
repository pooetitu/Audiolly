package com.audiolly.features.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.audiolly.R
import com.audiolly.api.TheAudioDBNetworkManager
import com.audiolly.features.artist.album.AlbumAdapter
import com.audiolly.features.artist.title.TitleAdapter
import com.audiolly.features.ranking.music.MusicRankingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.album_fragment.*
import kotlinx.android.synthetic.main.artist_fragment.*
import kotlinx.android.synthetic.main.artist_fragment.artist_name
import kotlinx.android.synthetic.main.artist_fragment.description
import kotlinx.android.synthetic.main.artist_fragment.return_button
import kotlinx.android.synthetic.main.music_ranking_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.artist_fragment, parent, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        GlobalScope.launch(Dispatchers.Default) {
            val artistId = arguments?.getString("artistId")
            val artist = TheAudioDBNetworkManager.getArtistDataAsync(artistId!!).artists[0]
            val albums = TheAudioDBNetworkManager.getArtistAlbumsAsync(artistId).albumsRanking
            val musics = TheAudioDBNetworkManager.getArtistTopMusic(artist.strMusicBrainzID).musics
            withContext(Dispatchers.Main) {
                Glide.with(artist_thumbnail.context)
                    .load(artist.strArtistThumb)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                    .placeholder(R.drawable.ic_placeholder_artist)
                    .into(artist_thumbnail)
                artist_name.text = artist.strArtist
                artist_location_genre.text = getString(R.string.location_genre, artist.strCountry, artist.strGenre)
                description.text = artist.strBiographyEN

                albums_count.text = getString(R.string.albums_count, albums.size)



                return_button.setOnClickListener {
                    view.findNavController()
                        .navigateUp()
                }
                albums_list.run {
                    layoutManager = GridLayoutManager(this@ArtistFragment.context,3,LinearLayoutManager.HORIZONTAL,false)
                    adapter = AlbumAdapter(albums)
                }
                appreciated_titles_list.run {
                    layoutManager = LinearLayoutManager(this@ArtistFragment.context)
                    adapter = TitleAdapter(musics)
                }
            }
        }
    }
}