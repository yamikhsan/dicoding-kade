package com.studio.yami.kadesubmission.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.entity.ListEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_league.*

class MyListAdapter(private val list: List<ListEntity>, private val listener: (ListEntity) -> Unit)
    : RecyclerView.Adapter<MyListAdapter.LeagueHolder>(){

    override fun onCreateViewHolder(view: ViewGroup, position: Int): LeagueHolder {
        val v = LayoutInflater.from(view.context).inflate(R.layout.item_league, view, false)
        return LeagueHolder(v)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: LeagueHolder, position: Int) {
        holder.onBind(list[position], listener)
    }

    class LeagueHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun onBind(list: ListEntity, listener: (ListEntity) -> Unit){
            tv_name_league.text = list.name
            Picasso.get()
                .load(list.logo)
                .error(R.drawable.ic_error)
                .fit()
                .into(iv_logo_league, object : Callback {
                    override fun onSuccess() {
                        prog_league_item.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {

                    }
                })

            containerView.setOnClickListener {
                listener(list)
            }
        }

    }


}