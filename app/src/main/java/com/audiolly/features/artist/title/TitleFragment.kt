package com.audiolly.features.artist.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.audiolly.R
import kotlinx.android.synthetic.main.music_ranking_fragment.*

class TitleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.music_ranking_fragment, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        main_list.run {
            layoutManager = LinearLayoutManager(this@TitleFragment.context)
            adapter = TitleAdapter()
        }
    }
}
