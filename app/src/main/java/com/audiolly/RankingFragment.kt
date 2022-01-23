package com.audiolly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.ranking_fragment.*

class RankingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ranking_fragment, parent, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        main_list.run {
            layoutManager = LinearLayoutManager(this@RankingFragment.context)
            adapter = ListAdapter()
            addItemDecoration(
                DividerItemDecoration(
                    this@RankingFragment.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }
}