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
import kotlinx.android.synthetic.main.artist_fragment.*

class ArtistFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.artist_fragment, parent, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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