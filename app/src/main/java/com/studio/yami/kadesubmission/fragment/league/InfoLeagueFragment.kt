package com.studio.yami.kadesubmission.fragment.league

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.viewmodel.MainViewModel
import com.studio.yami.kadesubmission.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_info_league.*

class InfoLeagueFragment: Fragment() {

    companion object {
        private const val EXTRA_ID = "EXTRA ID"
        @JvmStatic
        fun newInstance(id: Int): InfoLeagueFragment {
            return InfoLeagueFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_ID, id)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = activity?.application?.let { ViewModelFactory(it) }
        val model = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        val id = arguments?.getInt(EXTRA_ID, 0)

        model.lookUpLeague(id.toString()).observe(this, Observer {
            if(it != null){
                pro_header.visibility = View.GONE
                const_detail.visibility = View.VISIBLE

                tv_desc_content.text = it.strDescriptionEN
                tv_sport_content.text = it.strSport
                tv_formed_content.text = it.intFormedYear
                tv_gender_content.text = it.strGender
                tv_country_content.text = it.strCountry
            }
        })
    }

}

