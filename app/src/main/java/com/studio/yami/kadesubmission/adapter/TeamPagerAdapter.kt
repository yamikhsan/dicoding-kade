package com.studio.yami.kadesubmission.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.fragment.team.InfoTeamFragment
import com.studio.yami.kadesubmission.fragment.team.ListsPlayerFragment

private val tabTitle = arrayOf(
    R.string.player,
    R.string.detail
)

private val tabFragment = mutableListOf<Fragment>()

class TeamPagerAdapter(private val context: Context, fm: FragmentManager, id: Int): FragmentPagerAdapter(fm){

    init {
        val list = ListsPlayerFragment.newInstance(id)
        val info = InfoTeamFragment.newInstance(id)

        tabFragment.clear()
        tabFragment.add(list)
        tabFragment.add(info)
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