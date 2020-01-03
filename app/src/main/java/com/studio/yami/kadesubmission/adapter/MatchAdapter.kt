package com.studio.yami.kadesubmission.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.entity.league.MatchEntity
import com.studio.yami.kadesubmission.utils.MatchLayoutBind
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_match.*

class MatchAdapter(private val match: List<MatchEntity.Event>, private val listener: (MatchEntity.Event) -> Unit)
    : RecyclerView.Adapter<MatchAdapter.MatchHolder>(){

    override fun onCreateViewHolder(view: ViewGroup, position: Int): MatchHolder {
        val v = LayoutInflater.from(view.context).inflate(R.layout.item_match, view, false)
        return MatchHolder(v)
    }

    override fun getItemCount(): Int = match.size

    override fun onBindViewHolder(holder: MatchHolder, position: Int) {
        holder.onBind(match[position], listener)
    }

    class MatchHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun onBind(match: MatchEntity.Event, listener: (MatchEntity.Event) -> Unit){

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

            containerView.setOnClickListener {
                listener(match)
            }
        }

    }
}