package com.studio.yami.kadesubmission.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.studio.yami.kadesubmission.BuildConfig
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.entity.league.MatchEntity
import com.studio.yami.kadesubmission.entity.team.TeamEntity
import com.studio.yami.kadesubmission.repository.ApiRepository
import com.studio.yami.kadesubmission.sourcedata.api.apiRequest
import com.studio.yami.kadesubmission.sourcedata.api.myUrl
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchLayoutBind(
    match: MatchEntity.Event,
    iv_team_home: ImageView,
    iv_team_away: ImageView,
    prog_home_item: ProgressBar,
    prog_away_item: ProgressBar,
    tv_str_league: TextView,
    tv_str_date: TextView,
    tv_team_home: TextView,
    tv_team_away: TextView,
    tv_score_match: TextView
) {

    init {
        doAsync {
            val home = resTeam(match.idHomeTeam)
            val away = resTeam(match.idAwayTeam)

            uiThread {
                if(home.teams != null){
                    loadImage(home.teams[0], iv_team_home, prog_home_item)
                }
                if(away.teams != null){
                    loadImage(away.teams[0], iv_team_away, prog_away_item)
                }
            }
        }

        tv_str_league.text = match.strLeague
        tv_str_date.text = match.getDate()

        tv_team_home.text = match.strHomeTeam
        tv_team_away.text = match.strAwayTeam

        tv_score_match.text = match.getScore()
    }

    private fun resTeam(id: String): TeamEntity.Teams{
        val url = myUrl(BuildConfig.LOOKUP_TEAM, ApiRepository.pId, id)
        val json = apiRequest(url)
        return Gson().fromJson(json, TeamEntity.Teams::class.java)
    }

    private fun loadImage(team: TeamEntity.Team, view: ImageView, progress: ProgressBar){
        Picasso.get()
            .load(team.strTeamBadge)
            .error(R.drawable.ic_error)
            .fit()
            .into(view, object : Callback {
                override fun onSuccess() {
                    progress.visibility = View.GONE
                }

                override fun onError(e: Exception?) {

                }
            })
    }

}