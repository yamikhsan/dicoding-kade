package com.studio.yami.kadesubmission.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.entity.league.StandingsEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_standings.*

class StandingsAdapter(private val teams: List<StandingsEntity.Table>): RecyclerView.Adapter<StandingsAdapter.StandingsHolder>() {

    override fun onCreateViewHolder(view: ViewGroup, pos: Int): StandingsHolder {
        val v = LayoutInflater.from(view.context).inflate(R.layout.item_standings, view, false)
        return StandingsHolder(v)
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: StandingsHolder, pos: Int) {
        holder.onBind(teams[pos], pos + 1)
    }

    class StandingsHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun onBind(team: StandingsEntity.Table, pos: Int){
            tv_stand_no.text = pos.toString()
            tv_stand_club.text = team.name
            tv_stand_played.text = team.played.toString()
            tv_stand_win.text = team.win.toString()
            tv_stand_draw.text = team.draw.toString()
            tv_stand_loss.text = team.loss.toString()
            tv_stand_point.text = team.total.toString()
            tv_stand_goal_dif.text = team.goalsdifference.toString()
        }

    }
}