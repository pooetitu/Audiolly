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
import com.audiolly.features.ranking.music.MusicRankingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.album_fragment.*
import kotlinx.android.synthetic.main.album_fragment.artist_name
import kotlinx.android.synthetic.main.album_fragment.description
import kotlinx.android.synthetic.main.album_fragment.return_button
import kotlinx.android.synthetic.main.album_fragment.titles_list
import kotlinx.android.synthetic.main.artist_fragment.*
import kotlinx.android.synthetic.main.music_ranking_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.album_fragment, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val albumId = arguments?.getString("albumId")
        GlobalScope.launch(Dispatchers.Default) {
            val album = TheAudioDBNetworkManager.getAlbumDataAsync(albumId!!).albumsRanking[0]
            val musics = TheAudioDBNetworkManager.getAlbumMusicsAsync(albumId).musics
            withContext(Dispatchers.Main) {
                album_name.text = album.strAlbum
                artist_name.text = album.strArtist
                description.text = album.strDescriptionEN
                rate.text = album.intScore.toString()
                votes_count.text = getString(R.string.review_count, album.intScoreVotes)
                song_count.text = getString(R.string.songs_count,10)
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
                    adapter = TitleAdapter(musics)
                }
            }
        }
    }
}
