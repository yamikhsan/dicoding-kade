package com.studio.yami.kadesubmission.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.entity.league.MatchEntity
import com.studio.yami.kadesubmission.utils.MatchLayoutBind
import com.studio.yami.kadesubmission.viewmodel.MainViewModel
import com.studio.yami.kadesubmission.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_match.*
import kotlinx.android.synthetic.main.item_match.*
import kotlinx.android.synthetic.main.layout_match.*
import org.jetbrains.anko.toast

class MatchActivity : AppCompatActivity() {

    private var isSave = false
    private lateinit var model: MainViewModel
    private lateinit var event: MatchEntity.Event

    companion object{
        const val EXTRA_MATCH = "EXTRA MATCH"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory(application)
        model = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        takeIntent()

        floatingOnClick()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == android.R.id.home){
                onBackPressed()
                return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun takeIntent(){
        val match: MatchEntity.Event = intent.getParcelableExtra(EXTRA_MATCH)
        onBind(match)
    }

    private fun onBind(match: MatchEntity.Event){

        MatchLayoutBind(
            match,
            iv_team_home,
            iv_team_away,
            prog_home_item,
            prog_away_item,
            tv_str_league,
            tv_str_date,
            tv_team_home,
            tv_team_away,
            tv_score_match
        )

        event = match
        showEvent()
        checkFavorite()
    }



    private fun showEvent(){

        model.lookUpEvent(event.idEvent).observe(this, Observer {
            if(it != null){
                prog_match.visibility = View.GONE
                relative_match.visibility = View.VISIBLE

                tv_home_player_goal.text = toArrayPlayer(it.strHomeGoalDetails)
                tv_away_player_goal.text = toArrayPlayer(it.strAwayGoalDetails)

                tv_yellowcard_home.text = toArraySize(it.strHomeYellowCards)
                tv_yellowcard_away.text = toArraySize(it.strAwayYellowCards)

                tv_redcard_home.text = toArraySize(it.strHomeRedCards)
                tv_redcard_away.text = toArraySize(it.strAwayRedCards)

                tv_formation_home.text = toArraySize(it.strHomeFormation)
                tv_formation_away.text = toArraySize(it.strAwayFormation)
            }
        })

    }

    private fun floatingOnClick(){
        floating_btn_save.setOnClickListener {
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
            floating_btn_save.setImageResource(R.drawable.ic_favorite)
        }
        else{
            floating_btn_save.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun checkFavorite(){
        model.showEventFavoriteById(event.idEvent).observe(this, Observer {
            if (it != null) {
                isSave = it
                setSave()
            }
        })
    }

    private fun insertFavorite(){
        model.insertEventFavorite(event)
    }

    private fun deleteFavorite(){
        model.deleteEventFavorite(event.idEvent)
    }

    private fun toArraySize(str: String?): String{
        var res = "0"
        if(str != null){
            val arr = str.split(";").toMutableList()
            arr.removeAt(arr.size - 1)
            res = arr.size.toString()
        }
        return res
    }

    private fun toArrayPlayer(str: String?): String{
        var res = "-"
        if(str != null){
            val strBuilder = StringBuilder()
            val arr = str.split(";").toMutableList()
            arr.removeAt(arr.size - 1)
            arr.forEach {
                strBuilder.append(it + "\n")
            }
            res = strBuilder.toString()
        }
        return res
    }

}
