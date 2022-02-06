package com.audiolly.features.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.audiolly.R
import com.audiolly.features.artist.title.TitleAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.album_fragment.*
import kotlinx.android.synthetic.main.album_fragment.artist_name
import kotlinx.android.synthetic.main.album_fragment.description
import kotlinx.android.synthetic.main.album_fragment.return_button
import kotlinx.android.synthetic.main.album_fragment.titles_list
import kotlinx.android.synthetic.main.artist_fragment.*

class AlbumFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.album_fragment, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        album_name.text = "Revival"
        artist_name.text = "Eminem"
        description.text = "Lorem Ipsum is simply dummy text Lorem Ipsum is simply dummy text" +
                " Lorem Ipsum is simply dummy text"
        rate.text = "4.9"
        votes_count.text = getString(R.string.review_count, 348)
        song_count.text = getString(R.string.songs_count,10)
        Glide.with(album_thumbnail.context)
            .load("https://www.theaudiodb.com/images/media/album/thumb/twsyqy1513337658.jpg")
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .placeholder(R.drawable.ic_placeholder_album)
            .into(album_thumbnail)
        Glide.with(thumbnail_translucent.context)
            .load("https://www.theaudiodb.com/images/media/album/thumb/twsyqy1513337658.jpg")
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder_album)
            .into(thumbnail_translucent)
        return_button.setOnClickListener {
            view.findNavController()
                .navigateUp()
        }
        titles_list.run {
            layoutManager = LinearLayoutManager(this@AlbumFragment.context)
            adapter = TitleAdapter()
        }
    }
}
