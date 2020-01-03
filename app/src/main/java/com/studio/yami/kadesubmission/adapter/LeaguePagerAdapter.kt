package com.studio.yami.kadesubmission.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.fragment.league.InfoLeagueFragment
import com.studio.yami.kadesubmission.fragment.league.ListsTeamFragment
import com.studio.yami.kadesubmission.fragment.league.MatchFragment
import com.studio.yami.kadesubmission.fragment.league.StandingsFragment

private val tabTitle = arrayOf(
    R.string.match,
    R.string.standings,
    R.string.team,
    R.string.detail
)

private val tabFragment = mutableListOf<Fragment>()

class LeaguePagerAdapter(private val context: Context, fm: FragmentManager, id: Int) : FragmentPagerAdapter(fm) {

    init {
        val match = MatchFragment.newInstance(id)
        val stand = StandingsFragment.newInstance(id)
        val list = ListsTeamFragment.newInstance(id)
        val detail = InfoLeagueFragment.newInstance(id)

        tabFragment.clear()
        tabFragment.add(match)
        tabFragment.add(stand)
        tabFragment.add(list)
        tabFragment.add(detail)
    }

    override fun getItem(position: Int): Fragment {
        return tabFragment[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(tabTitle[position])
    }

    override fun getCount(): Int {
        return tabTitle.size
    }
}