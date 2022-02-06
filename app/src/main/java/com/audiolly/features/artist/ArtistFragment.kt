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
import com.audiolly.features.artist.album.AlbumAdapter
import com.audiolly.features.artist.title.TitleAdapter
import kotlinx.android.synthetic.main.album_fragment.*
import kotlinx.android.synthetic.main.artist_fragment.*
import kotlinx.android.synthetic.main.artist_fragment.artist_name
import kotlinx.android.synthetic.main.artist_fragment.description
import kotlinx.android.synthetic.main.artist_fragment.return_button

class ArtistFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.artist_fragment, parent, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        artist_name.text = "Khalid"
        artist_location_genre.text = getString(R.string.location_genre, "Toronto, Canada", "R&B")
        description.text = "description"
        albums_count.text = getString(R.string.albums_count, 12)
        return_button.setOnClickListener {
            view.findNavController()
                .navigateUp()
        }
        albums_list.run {
            layoutManager = GridLayoutManager(this@ArtistFragment.context,3,LinearLayoutManager.HORIZONTAL,false)
            adapter = AlbumAdapter()
        }
        appreciated_titles_list.run {
            layoutManager = LinearLayoutManager(this@ArtistFragment.context)
            adapter = TitleAdapter()
        }
    }
}