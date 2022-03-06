package com.audiolly.features.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.audiolly.AlbumArtistAdapter
import com.audiolly.R
import com.audiolly.models.Section
import com.audiolly.storage.DatabaseManager
import kotlinx.android.synthetic.main.favorites_fragment.*
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

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
            try {
                objectsList.add(Section(getString(R.string.artists)))
                objectsList.addAll(db.findAllArtists())
                objectsList.add(Section(getString(R.string.albums)))
                objectsList.addAll(db.findAllAlbums())
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
            } catch (e: HttpException) {
                Toast.makeText(context, e.message(), Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
            }
        }
    }
}