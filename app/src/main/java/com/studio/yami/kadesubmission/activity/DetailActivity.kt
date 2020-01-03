package com.studio.yami.kadesubmission.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.activity.MainActivity.Companion.EXTRA_DATA
import com.studio.yami.kadesubmission.adapter.LeaguePagerAdapter
import com.studio.yami.kadesubmission.entity.ListEntity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.layout_header.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar_detail)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar_detail.setNavigationIcon(R.drawable.ic_chevron_left)

        val white = ContextCompat.getColor(this, android.R.color.white)

        collapsing_toolbar_detail.apply {
            setCollapsedTitleTextColor(white)
            setExpandedTitleColor(white)
        }

        takeIntent()
    }

    private fun takeIntent(){
        val list: ListEntity = intent.getParcelableExtra(EXTRA_DATA)
        Picasso.get()
            .load(list.logo)
            .error(R.drawable.ic_error)
            .fit()
            .into(iv_img_header, object: Callback{
                override fun onSuccess() {
                    pro_header.visibility = View.GONE
                }

                override fun onError(e: Exception?) {

                }

            })

        collapsing_toolbar_detail.title = list.name
        tabbedAdapter(list.id.toInt())
    }

    private fun tabbedAdapter(id: Int){
        val leaguePagerAdapter = LeaguePagerAdapter(this, supportFragmentManager, id)
        view_pager.adapter = leaguePagerAdapter
        tabs.setupWithViewPager(view_pager)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}