package com.audiolly.features.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.audiolly.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
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
        viewpager.adapter = RankingAdapter(this)
        TabLayoutMediator(tabLayout, viewpager) { tab: TabLayout.Tab, i: Int ->
            if (i == 0) {
                tab.text = getString(R.string.titles)
            } else {
                tab.text = getString(R.string.albums)
            }
        }.attach()
    }
}
