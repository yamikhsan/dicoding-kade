package com.studio.yami.kadesubmission.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.adapter.TeamPagerAdapter
import com.studio.yami.kadesubmission.entity.ListEntity
import com.studio.yami.kadesubmission.viewmodel.MainViewModel
import com.studio.yami.kadesubmission.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_team.*
import kotlinx.android.synthetic.main.layout_header.*
import org.jetbrains.anko.toast

class TeamActivity : AppCompatActivity() {

    private var isSave = false
    private lateinit var model: MainViewModel
    private lateinit var team: ListEntity

    companion object{
        const val EXTRA_TEAM = "EXTRA TEAM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        setSupportActionBar(toolbar_team)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar_team.setNavigationIcon(R.drawable.ic_chevron_left)

        team = intent.getParcelableExtra(EXTRA_TEAM)
        bindHeader()
        tabbedAdapter()

        val white = ContextCompat.getColor(this, android.R.color.white)
        collapsing_toolbar_team.apply {
            setCollapsedTitleTextColor(white)
            setExpandedTitleColor(white)
            title = team.name
        }

        val factory = ViewModelFactory(application)
        model = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        checkFavorite()
        floatingOnClick()
    }

    private fun bindHeader(){
        Picasso.get()
            .load(team.logo)
            .error(R.drawable.ic_error)
            .fit()
            .into(iv_img_header, object: Callback {
                override fun onSuccess() {
                    pro_header.visibility = View.GONE
                }

                override fun onError(e: Exception?) {

                }
            })
    }

    private fun tabbedAdapter(){
        val id = team.id.toInt()
        val teamPagerAdapter = TeamPagerAdapter(this, supportFragmentManager, id)
        view_pager_team.adapter = teamPagerAdapter
        tabs_team.setupWithViewPager(view_pager_team)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == android.R.id.home){
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun floatingOnClick(){
        floating_save_team.setOnClickListener {
            isSave = !isSave
            setSave()
            if(isSave){
                insertFavorite()
                toast("insert")
            } else{
                deleteFavorite()
                toast("delete")
            }
        }
    }

    private fun setSave(){
        if (isSave){
            floating_save_team.setImageResource(R.drawable.ic_favorite)
        }
        else{
            floating_save_team.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun checkFavorite(){
        model.showTeamFavoriteById(team.id).observe(this, Observer {
            if (it != null) {
                isSave = it
                setSave()
            }
        })
    }

    private fun insertFavorite(){
        model.insertTeamFavorite(team)
    }

    private fun deleteFavorite(){
        model.deleteTeamFavorite(team.id)
    }

}
