package com.studio.yami.kadesubmission.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.fragment.favorite.FavEventFragment
import com.studio.yami.kadesubmission.fragment.favorite.FavTeamFragment

private val tabTitle = arrayOf(
    R.string.event,
    R.string.team
)

private val tabFragment = mutableListOf<Fragment>()

class FavoritePagerAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm){

    init {
        val event = FavEventFragment()
        val team = FavTeamFragment()

        tabFragment.clear()
        tabFragment.add(event)
        tabFragment.add(team)
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