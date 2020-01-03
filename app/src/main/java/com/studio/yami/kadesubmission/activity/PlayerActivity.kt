package com.studio.yami.kadesubmission.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.viewmodel.MainViewModel
import com.studio.yami.kadesubmission.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_info_player.*

class PlayerActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_PLAYER = "EXTRA PLAYER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        setSupportActionBar(toolbar_player)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar_player.setNavigationIcon(R.drawable.ic_chevron_left)

        val id = intent.getIntExtra(EXTRA_PLAYER, -1)

        getPlayer(id)
        Log.d("League", "activity/PlayerActivity/ >>$id<<")
    }

    private fun getPlayer(id: Int){

        val factory = ViewModelFactory(application)
        val model = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        model.lookUpPlayer(id.toString()).observe(this, Observer {

            if(it != null){
                pro_info_player.visibility = View.GONE
                scroll_info_player.visibility = View.VISIBLE

                if(it.players != null){
                    val player = it.players[0]
                    tv_desc_content_player.text = player.strDescription
                    tv_position_content_player.text = player.strPosition
                    tv_gender_content_player.text = player.strGender
                    tv_born_content_player.text = player.dateBorn
                    tv_nation_content_player.text = player.strNationality

                    val cutout = player.strCutout ?: "https://www.thesportsdb.com/images/media/player/cutout/5pywp41548775768.png"

                    Picasso.get()
                        .load(cutout)
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

            }

        })

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == android.R.id.home){
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}
