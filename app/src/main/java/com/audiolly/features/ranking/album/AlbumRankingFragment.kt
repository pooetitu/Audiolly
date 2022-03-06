package com.audiolly.features.ranking.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.audiolly.R
import com.audiolly.api.TheAudioDBNetworkManager
import com.audiolly.models.AlbumTrending
import kotlinx.android.synthetic.main.music_ranking_fragment.*
import kotlinx.coroutines.*

class AlbumRankingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.album_ranking_fragment, parent, false)
    }

    private var asyncTask: Job? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        asyncTask = GlobalScope.launch(Dispatchers.Default) {
            var albums: MutableList<AlbumTrending> = mutableListOf()
            try {
                albums = TheAudioDBNetworkManager.getAlbumRankingAsync().albumsRanking ?: albums
            } catch (e: Exception) {
            }
            albums.sortBy { it.intChartPlace }
            withContext(Dispatchers.Main) {
                main_list.run {
                    layoutManager = LinearLayoutManager(this@AlbumRankingFragment.context)
                    adapter = AlbumRankingAdapter(albums)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        asyncTask?.cancel()
    }
}