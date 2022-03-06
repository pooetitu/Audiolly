package com.audiolly.features.ranking.music

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.audiolly.R
import com.audiolly.api.TheAudioDBNetworkManager
import kotlinx.android.synthetic.main.music_ranking_fragment.*
import kotlinx.coroutines.*
import retrofit2.HttpException

class MusicRankingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.music_ranking_fragment, parent, false)
    }

    var asyncTask: Job? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        asyncTask = GlobalScope.launch(Dispatchers.Default) {
            try {
                val response = TheAudioDBNetworkManager.getMusicRankingAsync().musicsRanking
                response.sortBy { it.intChartPlace }
                withContext(Dispatchers.Main) {
                    main_list.run {
                        layoutManager = LinearLayoutManager(this@MusicRankingFragment.context)
                        adapter = MusicRankingAdapter(response)
                    }
                }
            } catch (e: HttpException) {
                Toast.makeText(context, e.message(), Toast.LENGTH_LONG).show()
            } catch (e: Exception) { }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        asyncTask?.cancel()
    }
}
