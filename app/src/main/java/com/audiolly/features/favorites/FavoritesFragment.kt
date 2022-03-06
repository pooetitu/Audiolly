package com.audiolly.features.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.audiolly.AlbumArtistAdapter
import com.audiolly.R
import com.audiolly.models.Album
import com.audiolly.models.Artist
import com.audiolly.models.Section
import com.audiolly.storage.DatabaseManager
import kotlinx.android.synthetic.main.favorites_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorites_fragment, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = DatabaseManager(requireContext())
        val objectsList = mutableListOf<Any>()
        GlobalScope.launch(Dispatchers.Default) {
            var artists: List<Artist> = listOf()
            var albums: List<Album> = listOf()
            try {
                artists = db.findAllArtists()
                albums = db.findAllAlbums()
            } catch (e: Exception) {
            }
            objectsList.add(Section(getString(R.string.artists)))
            objectsList.addAll(artists)
            objectsList.add(Section(getString(R.string.albums)))
            objectsList.addAll(albums)
            withContext(Dispatchers.Main) {
                favorites_list.run {
                    layoutManager = LinearLayoutManager(this@FavoritesFragment.context)
                    adapter = AlbumArtistAdapter(
                        objectsList,
                        R.id.action_tab_favorites_to_artistFragment,
                        R.id.action_tab_favorites_to_albumFragment
                    )
                }
            }
        }
    }
}