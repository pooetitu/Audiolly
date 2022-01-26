package com.audiolly.features.ranking

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.audiolly.features.ranking.album.AlbumRankingFragment
import com.audiolly.features.ranking.music.MusicRankingFragment

class RankingAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            MusicRankingFragment()
        } else {
            AlbumRankingFragment()
        }
    }
}