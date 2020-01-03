package com.studio.yami.kadesubmission.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.adapter.FavoritePagerAdapter
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        setSupportActionBar(toolbar_favorite)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar_favorite.setNavigationIcon(R.drawable.ic_chevron_left)
        toolbar_favorite.setTitle(R.string.favorite)

    }

    override fun onStart() {
        super.onStart()
        tabbedAdapter()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tabbedAdapter(){
        val teamPagerAdapter = FavoritePagerAdapter(this, supportFragmentManager)
        view_pager_favorite.adapter = teamPagerAdapter
        tabs_favorite.setupWithViewPager(view_pager_favorite)
    }

}
